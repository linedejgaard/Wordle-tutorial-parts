public class Feedback {
    private Response[] letterResponses; //is guess: gray, yellow, green
    private Response[][] guessResponses; //is guess: gray, yellow, green
    private Word secretWord;
    
    public Feedback(int totalGuesses, Word sWord) {
        this.secretWord = sWord;

        letterResponses = new Response[26];
        guessResponses = new Response[totalGuesses][sWord.length()];

        initLetterResponse();
        initGuessResponse();
    }

    private void initLetterResponse() {
        for (int i = 0; i < letterResponses.length; i++) {
            letterResponses[i] = Response.UNKNOWN;
        }
    }

    private void initGuessResponse() {
        for (int row = 0; row < guessResponses.length; row++) {
            for (int col = 0; col < guessResponses[row].length; col++) {
                guessResponses[row][col] = Response.UNKNOWN;
            }
        }
    }

    private void printResponses() {
        System.out.println("\nBOARD RESPONSES:");
        for (int i = 0; i < guessResponses.length; i++) {
            for (int j = 0; j < guessResponses[i].length; j++) {
                System.out.print(guessResponses[i][j].name() + " ");
            }
            System.out.println();
        }
    }

    private void printLetterResponses() {
        System.out.println("\nLETTER RESPONSES:");
        int letter = 65;
        for (Response r : letterResponses) {
            System.out.print(((char) letter) +": " + r.name() + " ");
            letter++;
        }
        System.out.println();
    }

    public Response[] getLetterResponses() {
        return letterResponses;
    }

    public Response[][] getGussResponses() {
        return guessResponses;
    }

    public Response getLetterResponse(int index) {
        return letterResponses[index];
    }

    public Response getGuessResponse(int row, int col) {
        return guessResponses[row][col];
    }

    private Response getLetterResponse(String currSecret, int index, Response currentLetterResponse, char letter) {
        if (currSecret.charAt(index) == letter) {
            return Response.CORRECT;
        } else if (secretWord.contains("" + letter) && (currentLetterResponse != Response.CORRECT)) { //green cannot be yellow again
            return Response.ALMOST_CORRECT;
        } else if (currentLetterResponse == Response.UNKNOWN){
            return Response.WRONG;
        }
        return Response.UNKNOWN;
    }

    public void updateResponses(Word currentGuess, int numGuess) {
        String currWord = currentGuess.toString();
        String currSecret = secretWord.toString();

        for (int i = 0; i < currWord.length(); i++) {

            char letter = currWord.charAt(i);
            int asciicode = letter;
            Response currentLetterResponse = letterResponses[asciicode-65];

            letterResponses[asciicode-65] = getLetterResponse(currSecret, i, currentLetterResponse, letter);
            printLetterResponses();
        }
        guessResponses[numGuess] = secretWord.getLetterResponses(currentGuess);
        printResponses();
    }

}
