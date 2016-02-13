package Encryption;

import Data.QuestionAnswer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Cabbibi on 11/02/2016.
 */
public class BaseEncryption {

    protected RSA rsa;

    public BaseEncryption(BigInteger e, BigInteger n) {
        this.rsa = new RSA(n,e);
    }

    public BaseEncryption(int bitLength) {
        this.rsa = new RSA(bitLength);
    }

    public ArrayList<BigInteger> convertArrayToBigInteger(ArrayList<QuestionAnswer> questionAnswersArray)
    {
        ArrayList<BigInteger> returnData = new ArrayList<>();
        returnData.addAll(questionAnswersArray.stream().map(q -> new BigInteger(q.getAnswers().getBytes())).collect(Collectors.toList()));
        return returnData;
    }

    public ArrayList<BigInteger> generateRandomArrayNumber(int number)
    {
        ArrayList<BigInteger> returnData = new ArrayList<>();
        for(int i = 0; i < number; i++)
        {
            returnData.add(new BigInteger(256, new Random()));
        }
        return returnData;
    }

    public RSA getRsa() {
        return rsa;
    }

    public BigInteger generateRandomBigInteger()
    {
        return new BigInteger(256, new Random());
    }
}
