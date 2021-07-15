package app;

import syed.code.core.Util;

import java.util.ArrayList;
import java.util.Arrays;

public class HTMLDataStorage {

    String description;
    ArrayList<String> imagesURLs;
    ArrayList<String> notes;
    ArrayList<String> codeSamples;
    int typeOfOutput; // 0 1 2
    ArrayList<String> sampleInputs;
    ArrayList<String> sampleOutputs;
    String singleExpectedOutput;

    HTMLDataStorage() {
        description = "";
        imagesURLs = new ArrayList<>();
        notes = new ArrayList<>();
        codeSamples = new ArrayList<>();
        typeOfOutput = -1;
        sampleInputs = new ArrayList<>();
        sampleOutputs = new ArrayList<>();
        singleExpectedOutput = "";
    }


    public boolean isReady() {
        boolean ready = getDescription() != null &&
                        getTypeOfOutput() != -1 &&
                        getImagesUrls() != null &&
                        getNotes() != null &&
                        getCodeSamples() != null;
        if (singleExpectedOutput.isBlank()) {
            return ready && getSampleInputs().length > 0 && getSampleOutputs().length > 0;
        }
        return ready;
    }

    void print() {
        Util.ECHO("----------------------------\n");
        Util.ECHO("Description: "+getDescription());
        Util.ECHO("TypeOfOutput: "+getTypeOfOutput());
        Util.ECHO("SingleOutput: "+getSingleExpectedOutput());
        Util.ECHO("Images: "+ Arrays.toString(getImagesUrls()));
        Util.ECHO("Notes: "+ Arrays.toString(getNotes()));
        Util.ECHO("CodeSamples: "+ Arrays.toString(getCodeSamples()));
        Util.ECHO("Sample Inputs: "+ Arrays.toString(getSampleInputs()));
        Util.ECHO("Sample Outputs: "+ Arrays.toString(getSampleOutputs()));
        Util.ECHO("----------------------------\n");

    }


    String getDescription() {
        return description;
    }

    int getTypeOfOutput() {
        return typeOfOutput;
    }

    String getSingleExpectedOutput() {
        return singleExpectedOutput;
    }

    String[] getImagesUrls() {
        String[] urls = new String[imagesURLs.size()];
        int i = 0;
        for (String url : imagesURLs) { urls[i++] = url; }
        return urls;
    }

    String[] getNotes() {
        String[] n = new String[notes.size()];
        int i = 0;
        for (String url : notes) { n[i++] = url; }
        return n;
    }

    String[] getCodeSamples() {
        String[] n = new String[codeSamples.size()];
        int i = 0;
        for (String url : codeSamples) { n[i++] = url; }
        return n;
    }

    String[] getSampleInputs() {
        String[] n = new String[sampleInputs.size()];
        int i = 0;
        for (String url : sampleInputs) { n[i++] = url; }
        return n;
    }

    String[] getSampleOutputs() {
        String[] n = new String[sampleOutputs.size()];
        int i = 0;
        for (String url : sampleOutputs) { n[i++] = url; }
        return n;
    }
}
