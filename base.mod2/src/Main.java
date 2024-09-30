public class Main {
    public static void main (String[] args) {
        Player player = new Player(Variant.PAPER, "alex");
        Player bot = new Player();
        System.out.println(bot.whoWins(player));
    }
}