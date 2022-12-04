import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import screen.Renderer;
import screen.Screen;

import java.util.logging.Logger;
import seasonlogic.models.PlayerProfile;

public class Main {
    public static void main(String[] args) throws IOException {

//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("save.txt"));
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("save.txt"));
//
//        List<PlayerProfile> savedProfiles = getProfiles();
//
//        for(PlayerProfile profile : savedProfiles){
//            bufferedWriter.write(profile.toSaveString());
//            bufferedWriter.write("\n");
//        }
//        bufferedWriter.close();
//
//        List<PlayerProfile> loadedProfiles = new ArrayList<>();
//
//        String line = null;
//
//        while((line = bufferedReader.readLine()) != null){
//            loadedProfiles.add(new PlayerProfile(line));
//        }
//
//        bufferedReader.close();
//
//        Collections.sort(savedProfiles);
//        Collections.sort(loadedProfiles);
//
//        System.out.println(savedProfiles);
//        System.out.println(loadedProfiles);

        Renderer.getInstance().renderFrames();
    }







    private static List<PlayerProfile> getProfiles(){

        List<PlayerProfile> profiles = new ArrayList<>();

        PlayerProfile chicken = new PlayerProfile("Chicken", 7);

        chicken.setSeed(3);

        chicken.receiveSeasonWin();
        chicken.receiveSeasonWin();
        chicken.receiveSeasonLoss();
        chicken.receiveSeasonWin();
        chicken.receiveSeasonWin();
        chicken.receiveSeasonLoss();
        chicken.receiveSeasonWin();
        chicken.receiveSeasonWin();
        chicken.receiveSeasonLoss();
        chicken.receiveSeasonWin();
        chicken.receiveSeasonWin();
        chicken.receiveSeasonLoss();
        chicken.receiveSeasonWin();
        chicken.receiveSeasonWin();
        chicken.receiveSeasonLoss();
        profiles.add(chicken);

        PlayerProfile chocolate = new PlayerProfile("Chocolate", 5);

        chocolate.setSeed(2);

        chocolate.receiveSeasonWin();
        chocolate.receiveSeasonWin();
        chocolate.receiveSeasonLoss();
        chocolate.receiveSeasonWin();
        chocolate.receiveSeasonLoss();
        chocolate.receiveSeasonLoss();
        chocolate.receiveSeasonWin();
        chocolate.receiveSeasonWin();
        chocolate.receiveSeasonLoss();
        chocolate.receiveSeasonLoss();
        chocolate.receiveSeasonWin();
        chocolate.receiveSeasonLoss();
        chocolate.receiveSeasonWin();
        chocolate.receiveSeasonWin();
        chocolate.receiveSeasonLoss();
        profiles.add(chocolate);

        PlayerProfile bigTeeim = new PlayerProfile("Big Teeim", 5);

        bigTeeim.setSeed(2);

        bigTeeim.receiveSeasonLoss();
        bigTeeim.receiveSeasonLoss();
        bigTeeim.receiveSeasonLoss();
        bigTeeim.receiveSeasonLoss();
        bigTeeim.receiveSeasonLoss();
        bigTeeim.receiveSeasonLoss();
        bigTeeim.receiveSeasonWin();
        bigTeeim.receiveSeasonWin();
        bigTeeim.receiveSeasonLoss();
        bigTeeim.receiveSeasonLoss();
        bigTeeim.receiveSeasonWin();
        bigTeeim.receiveSeasonLoss();
        bigTeeim.receiveSeasonLoss();
        bigTeeim.receiveSeasonLoss();
        bigTeeim.receiveSeasonLoss();
        profiles.add(bigTeeim);

        return profiles;
    }
}
