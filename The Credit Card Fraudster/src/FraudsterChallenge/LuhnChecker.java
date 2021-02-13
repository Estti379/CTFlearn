package FraudsterChallenge;

public class LuhnChecker {

    protected LuhnChecker(){
        //empty
    }


    protected int getPartialLuhn(Integer[] cardNumber) {
        int partialLuhnNumber = 0;

        for (int i = 0; i < cardNumber.length; i++) {
            if(cardNumber[i] != null){
                partialLuhnNumber += collapsedLuhn(cardNumber[i] * getWeight(i) );
            }
        }

        return partialLuhnNumber;
    }

    private int collapsedLuhn(int myNumber) {
        if(myNumber < 10) {
            return myNumber;
        } else {
            return myNumber - 10 + 1;
        }
    }

    private int getWeight(int index) {
        if (index % 2 == 0) {
            return 1;
        } else {
            return 2;
        }
    }
}
