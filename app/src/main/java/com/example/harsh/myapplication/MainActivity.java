package com.example.harsh.myapplication;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoIncompatibleDriverException;
import com.mongodb.MongoTimeoutException;

public class MainActivity extends AppCompatActivity {

    final static String DATABASE_URI="mongodb://192.168.43.86:27017";
    final static String DATABASE_NAME="learning_mongo";
    final static String COLLECTION_NAME1="tours";
    public TextView userInput;
    public Button button;
    public String med = "Medium";
    DBCollection collection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        userInput = (TextView) findViewById(R.id.textView);
        userInput.setText("");
        button = (Button)findViewById(R.id.button);

        View.OnClickListener ourOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            try{
        MongoClientURI mongoClientURI = new MongoClientURI(DATABASE_URI);
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        DB db = mongoClient.getDB(DATABASE_NAME);
        collection = db.getCollection(COLLECTION_NAME1);

        DBCursor cursor = collection.find(new BasicDBObject("tourDifficulty",med));

        if(cursor.count()>0){
            userInput.setText("Connected successfully");
            mongoClient.close();
        }else{
            userInput.setText("No connection");
            mongoClient.close();
        }}
        catch (MongoTimeoutException mte) {
            mte.printStackTrace();
        } catch (MongoIncompatibleDriverException mide) {
            mide.printStackTrace();
        } catch (Exception exe) {
            exe.printStackTrace();
        }



            }
        };
        button.setOnClickListener(ourOnClickListener);


    }


}
