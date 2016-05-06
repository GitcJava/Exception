package util;

public class BraceChecker {
    public static final String MESSAGE_NO_ERROR  = "No Error";

    private Stack<BracketItem> stack;
    private String resultMessage;

    private BraceChecker() {
        stack = new StackImpl<BracketItem>();
    }



    public String getResultMessage() {
        return resultMessage;
    }

    public boolean parse(String inputText) {
        this.getResultMessage();
        reset();
        int length = inputText.length();
        int numberInLine = 0;
        int lineNumber = 1;
        boolean isParseCheck = true;

        BracketItem stackLastElement = null;
        BracketItem currentItem = null;
        char curr = 0;
        int i = 0;
        lab:
        for (; i < length; i++) {
            curr = inputText.charAt(i);
            numberInLine++;
            switch (curr) {
                case '\n':
                case'\r':
                    lineNumber++;
                    numberInLine = 0;
                    break;
                case '{':
                case '(':
                case '[':
                    stack.push(new BracketItem(curr, i, numberInLine, lineNumber));
                    break;
                case '}':
                    stackLastElement = stack.pop();
                    if (stackLastElement == null || stackLastElement.getValue() != '{') {
                        currentItem = new BracketItem(curr, i, numberInLine, lineNumber) ;
                        break lab;
                    }
                    break;
                case ']':
                    stackLastElement = stack.pop();
                    if (stackLastElement == null || stackLastElement.getValue() != '[') {
                        currentItem = new BracketItem(curr, i, numberInLine, lineNumber) ;
                        break lab;
                    }
                    break;
                case ')':
                    stackLastElement = stack.pop();
                    if (stackLastElement == null || stackLastElement.getValue() != '(') {
                        currentItem = new BracketItem(curr, i, numberInLine, lineNumber) ;
                        break lab;
                    }
                    break;
            }
        }

        if (i < length) {
            isParseCheck = false;
            if (stackLastElement == null) {
                resultMessage = "Error: Closed '" + currentItem + "' but not opened ";
            } else {
                resultMessage = "Error: Opened '" + stackLastElement + "' but closed '" + currentItem + "'wrong";
            }
        } else if (!stack.isEmpty()) {
            isParseCheck = false;
            stackLastElement = stack.pop();
            resultMessage = "Error: Opened '" + stackLastElement + "' but not closed ";
        }
        return isParseCheck;
    }

    private void reset() {
        stack.reset();
        resultMessage = MESSAGE_NO_ERROR;
    }


     public static class SingleObjectCreater {

        private static final BraceChecker braceChecker = new BraceChecker();

    public static BraceChecker getBraceChecker() {
        return braceChecker;
    }
}

    public static class BracketItem {

        private int index;
        private int numberInLine;
        private int lineNumber;
        private char value;

        public BracketItem(char value, int index, int numberInLine, int lineNumber) {
            this.index = index;
            this.numberInLine = numberInLine;
            this.lineNumber = lineNumber;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public int getNumberInLine() {
            return numberInLine;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public char getValue() {
            return value;
        }

        @Override
        public String toString() {
            return " '" + value +
                    "', number in line=" + numberInLine +
                    ", line number=" + lineNumber + ' ';
        }
    }
}