package pl.apka.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("pl.apka.sharedpreferences", Context.MODE_PRIVATE);

        
        ArrayList<String> friends = new ArrayList<>();

        friends.add("Jan");
        friends.add("Ola");
        friends.add("Ala");
        friends.add("Michał");
        friends.add("Grześ");
        friends.add("Jan");
        friends.add("Ola");
        friends.add("Ala");
        friends.add("Michał");
        friends.add("Grześ");

        try {
            String friendsStr = ObjectSerializer.serialize(friends);
            sharedPreferences.edit().putString("friends",friendsStr).apply();

            Log.e("FRIENDS",friendsStr);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ArrayList<String> newFriends = new ArrayList<>();

        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String friend : newFriends) {
            Log.e("NEW FRIENDS", friend);
        }
        //sharedPreferences.edit().putString("username","Jan").apply();

        //String username = sharedPreferences.getString("username","");

        //Log.e("username is: ",username);

    }
}
