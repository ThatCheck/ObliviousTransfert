package Encryption;

import Data.QuestionAnswer;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Cabbibi on 11/02/2016.
 */
public class ObliviousTransfertSender extends BaseEncryption {

    private ArrayList<QuestionAnswer> listQuestionAnswer;
    private ArrayList<BigInteger> generatedAnswers;
    private ArrayList<BigInteger> generatedRandom;
    private ArrayList<BigInteger> mValuesAlice;
    private ArrayList<BigInteger> kValuesAlice;

    public ObliviousTransfertSender(int bitLength) {
        super(bitLength);
    }

    public void generateEncryptedAndRandomAnswers()
    {
        generatedAnswers = this.convertArrayToBigInteger(this.listQuestionAnswer);
        generatedRandom = this.generateRandomArrayNumber(this.listQuestionAnswer.size());
    }

    public void generateKAndMValue(BigInteger vValue)
    {
        kValuesAlice = new ArrayList<>();
        int iterator = 0;
        for(BigInteger answer : generatedRandom){
            kValuesAlice.add(vValue.subtract(answer).modPow(this.getRsa().getD(), this.getRsa().getN()));
        }

        mValuesAlice = new ArrayList<>();
        for(int i = 0; i < this.listQuestionAnswer.size(); i++)
        {
            mValuesAlice.add(kValuesAlice.get(i).add(generatedAnswers.get(i)));
        }
    }

    public ArrayList<QuestionAnswer> getListQuestionAnswer() {
        return listQuestionAnswer;
    }

    public void setListQuestionAnswer(ArrayList<QuestionAnswer> listQuestionAnswer) {
        this.listQuestionAnswer = listQuestionAnswer;
    }

    public ArrayList<BigInteger> getGeneratedAnswers() {
        return generatedAnswers;
    }

    public ArrayList<BigInteger> getGeneratedRandom() {
        return generatedRandom;
    }

    public ArrayList<BigInteger> getmValuesAlice() {
        return mValuesAlice;
    }
}
