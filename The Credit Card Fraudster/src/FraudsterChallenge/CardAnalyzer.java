package FraudsterChallenge;

public class CardAnalyzer {

    Integer[] cardNumber = null;
    Integer[] cardNumberMask = null;

    public CardAnalyzer(){
        //empty
    }

    public void start(String unfinishedCreditCardNumber){
        importNumber(unfinishedCreditCardNumber);
        findCardNumber();
    }


    private void importNumber(String incompleteNumber) {
        int j = 0;
        cardNumber = new Integer[incompleteNumber.length()];
        cardNumberMask = new Integer[incompleteNumber.length()];
        for (int i = incompleteNumber.length() -1; i >= 0 ; i--) {
            if( incompleteNumber.charAt(i) >= '0' && incompleteNumber.charAt(i) <= '9' ){
                cardNumber[j] = Character.getNumericValue(incompleteNumber.charAt(i));
                cardNumberMask[j] = null;
            } else {
                cardNumber[j] = null;
                cardNumberMask[j] = 0;
            }
            j++;
        }
    }

    private void findCardNumber() {
        LuhnChecker luhnObject = new LuhnChecker();
        int luhnWithoutMask = luhnObject.getPartialLuhn(cardNumber);
        int luhnMask;

        boolean done = false;
        while(!done){
            luhnMask = luhnObject.getPartialLuhn(cardNumberMask);
            if ( (luhnWithoutMask + luhnMask) % 10 == 0){
                testForMultiple();
            }

            done = !continueIncrement();
            incrementMask();
        }
    }

    //TODO delete this
    private String getArrayInString() {
        String number = "";
        for (int i = cardNumber.length - 1; i >= 0; i--) {
            if(cardNumber[i] != null) {
                number += cardNumber[i].toString();
            } else {
                number += cardNumberMask[i].toString();
            }
        }

        return number;
    }

    private void testForMultiple() {
        long creditCardNumber = getFullNumber();
        if(creditCardNumber % 123457 == 0) {
            System.out.println("Possible credit card number: " + creditCardNumber);
        }
    }

    private long getFullNumber() {
        long creditCardNumber = 0;
        for (int i = 0; i < cardNumber.length; i++) {
            if(cardNumber[i] != null) {
                creditCardNumber += cardNumber[i] * Math.pow(10, i);
            } else {
                creditCardNumber += cardNumberMask[i] * Math.pow(10, i);
            }
        }

        return creditCardNumber;
    }

    private boolean continueIncrement() {
        boolean continueIncrement = false;
        for (int i = 0; i < cardNumberMask.length; i++) {
            if( (cardNumberMask[i] != null) && (cardNumberMask[i] != 9) ) {
                continueIncrement = true;
            }
        }

        return continueIncrement;
    }

    private void incrementMask() {
        int carryOver = 1;
        for (int i = 0; i < cardNumberMask.length; i++) {
            if(cardNumberMask[i] != null) {
                cardNumberMask[i] += carryOver;
                if(cardNumberMask[i] > 9) {
                    cardNumberMask[i] = 0;
                    carryOver = 1;
                } else {
                    carryOver = 0;
                }
            }
        }

    }


}
