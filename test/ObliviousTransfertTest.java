import Data.QuestionAnswer;
import Encryption.ObliviousTransfertReceiver;
import Encryption.ObliviousTransfertSender;
import Encryption.RSA;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Cabbibi on 10/02/2016.
 */
public class ObliviousTransfertTest {

    private ArrayList<QuestionAnswer> listQuestionAnswer;
    private int selectedAnswers;
    @Before
    public void initialize()
    {
        this.selectedAnswers = 2;
        this.listQuestionAnswer = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            this.listQuestionAnswer.add(new QuestionAnswer("Question "+ i, "Réponse "+ i));
        }
    }

    @Test
    public void testObliviousTransfert()
    {
        ObliviousTransfertSender obliviousTransfertSender = new ObliviousTransfertSender(256);
        System.out.println("Generate Question and Random answers");
        obliviousTransfertSender.setListQuestionAnswer(this.listQuestionAnswer);
        obliviousTransfertSender.generateEncryptedAndRandomAnswers();

        System.out.println("Generate K values");
        ObliviousTransfertReceiver obliviousTransfertReceiver = new ObliviousTransfertReceiver(obliviousTransfertSender.getRsa().getE(), obliviousTransfertSender.getRsa().getN());
        obliviousTransfertReceiver.setSelectedQuestion(this.selectedAnswers);
        obliviousTransfertReceiver.receive(obliviousTransfertSender.getGeneratedRandom());

        System.out.println("Generate K and M values");
        obliviousTransfertSender.generateKAndMValue(obliviousTransfertReceiver.getValueVBob());

        System.out.println("Decode");
        obliviousTransfertReceiver.decode(obliviousTransfertSender.getmValuesAlice());

        assertEquals(this.listQuestionAnswer.get(this.selectedAnswers).getAnswers(), obliviousTransfertReceiver.decode(obliviousTransfertSender.getmValuesAlice()));
    }
}