package app;

import java.util.ArrayList;

public class HTMLDataStorage {

    String description;
    ArrayList<String> imagesURLs;
    ArrayList<String> notes;
    ArrayList<String> codeSamples;
    int typeOfOutput; // -1 0 1
    ArrayList<String> sampleInputs;
    ArrayList<String> sampleOutputs;
    String singleExpectedOutput;

    HTMLDataStorage() {
        description = "";
        imagesURLs = new ArrayList<>();
        notes = new ArrayList<>();
        codeSamples = new ArrayList<>();
        typeOfOutput = Integer.MIN_VALUE;
        sampleInputs = new ArrayList<>();
        sampleOutputs = new ArrayList<>();
        singleExpectedOutput = "";
    }


    String[] getImagesUrls() {
        String[] urls = new String[imagesURLs.size()];
        for (int i = 0; i < imagesURLs.size(); i++) {
            if (imagesURLs.get(i) != null) {
                urls[i] = imagesURLs.get(i);
            }
        }
        return urls;
    }


}
