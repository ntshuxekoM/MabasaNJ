public class MainActivity extends Activity {
    TextView output;
    Button button_test;
    List<Flower> allFlowers = new ArrayList<Flower>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView)findViewById(R.id.tvOutput);
        //output.setMovementMethod(new ScrollingMovementMethod());
        
        button_test = (Button)findViewById(R.id.btnTest);
        button_test.setOnClickListener(new View.OnClickListener() {
			@Override 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isOnline()) {
					requestData("http://services.hanselandpetal.com/feeds/flowers.json");
				} else {
                    Toast.makeText(getBaseContext(),"Xava Data handle ka swona no chatting", Toast.LENGTH_LONG).show();
				}
				
			}

			private void requestData(String url) {
				myTask task = new myTask();
				task.execute(url); // Kgothalang
			}
		});
    }
    protected boolean isOnline()
    {
    	ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo netInfo = cm.getActiveNetworkInfo();
    	
    	if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		} else {
            return false;
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    protected void updateDisplay(String msg)
    {
    	//output.append(msg + "\n");
    }
    public class myTask extends AsyncTask<String, String, String>{
        @Override
        protected void onPreExecute() {
        	
        	//updateDisplay("Starting task");
        }
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String content = HttpManager.getData(params[0]);
			return content;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			allFlowers = FlowerJOSONParse.parseFeed(result);
			updateDisplay();
			
		}
		private void updateDisplay() {
			// TODO Auto-generated method stub
			if (allFlowers != null) {
				for (Flower flower : allFlowers) {
					output.append(flower.getName());
					output.append(flower.getCategory());
					output.append(flower.getPrice().toString());
					output.append("=============================");
				}
			}
		}
    	
    }