package Encryption;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Cabbibi on 11/02/2016.
 */
public class ObliviousTransfertReceiver extends BaseEncryption {

    private int selectedQuestion;
    private ArrayList<BigInteger> randomAnswers;
    private BigInteger valueKBob;
    private BigInteger valueVBob;

    public ObliviousTransfertReceiver(BigInteger e, BigInteger n) {
        super(e, n);
    }

    public int getSelectedQuestion() {
        return selectedQuestion;
    }

    public void setSelectedQuestion(int selectedQuestion) {
        this.selectedQuestion = selectedQuestion;
    }

    public BigInteger getValueKBob() {
        return valueKBob;
    }

    public BigInteger getValueVBob() {
        return valueVBob;
    }

    public void receive(ArrayList<BigInteger> randomAnswers)
    {
        this.randomAnswers = randomAnswers;
        valueKBob = this.generateRandomBigInteger();
        BigInteger exponentValue = valueKBob.pow(this.getRsa().getE().intValue());
        valueVBob = exponentValue.add(this.randomAnswers.get(this.selectedQuestion)).mod(this.getRsa().getN());
    }

    public String decode(ArrayList<BigInteger> mValue){
        BigInteger questionChoosen = mValue.get(this.selectedQuestion).subtract(valueKBob);
        return new String(questionChoosen.toByteArray());
    }
}
