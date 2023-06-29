
public class Main {
    public static void main(String[] args)
    {
    HangMan hm = new HangMan(args[0], new Player(args[args.length-2]), new Player(args[args.length-1]));
    hm.play();
    }
}

// new Random().nextInt(args.length-2)