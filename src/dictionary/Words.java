/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

/**
 *
 * @author Thanh Diệp
 */



public class Words {

	private String word, mean, example;
	
	public String getWord() {
		return this.word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getMean() {
		return this.mean;
	}
	public void setMean(String mean) {
		this.mean = mean;
	}
	public String getExample() {
		return this.example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public Words() {}
	public Words(String word, String mean, String example) {

		this.word = word;
		this.mean = mean;
		this.example = example;
	}

    @Override
    public String toString() {
        return "word = " + word + ", mean = " + mean + ", example = " + example+"\n";
    }
        
	
}

