/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 *
 * @author Thanh Diệp
 */
public class VoiceSpeech {
    private String text,no;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public VoiceSpeech() {
    }
    
    public VoiceSpeech(String no, String text) {
        this.text = text;
        this.no = no;
    }
    
    public void speech(String no, String text, int rate){
        VoiceSpeech v = new VoiceSpeech(no, text);
        
        System.setProperty("freetts.voices", "de.dfki.lt.freetts.en.us.MbrolaVoiceDirectory");
        System.setProperty("mbrola.base", "D:\\My Study\\JAVA\\BTL_JAVA\\JAVA_speech\\mbrola");
        VoiceManager vm = VoiceManager.getInstance();
        if(no.equals("1")){
            Voice voice = vm.getVoice("mbrola_us1");
            voice.allocate();
            voice.setRate(rate);
            voice.speak(text);
            voice.deallocate();
        }
        else if (no.equals("2")){
            Voice voice = vm.getVoice("mbrola_us2");
            voice.allocate();
            voice.setRate(rate);
            voice.speak(text);
            voice.deallocate();
        }
        else if(no.equals("3")){
            Voice voice = vm.getVoice("mbrola_us3");
            voice.allocate();
            voice.setRate(rate);
            voice.speak(text);
            voice.deallocate();
        }

    }
    
}
