public class Player {
    private String name;
    private int lives;
    public Player(String name){
        this.name = name;
        lives = 5;
    }
    public int getLives(){
     return lives;
    }

    public String getName(){
        return name;
    }
    public void decreaseLives(){
        lives--;
    }
}
