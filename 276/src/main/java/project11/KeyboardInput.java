package project11;

public class KeyboardInput {
    private String input;

    public void onButtonPressed() {
        System.out.println("Button pressed. Current input: " + input);
    }

    public void setInput(String input) {
        this.input = input;
    }
}