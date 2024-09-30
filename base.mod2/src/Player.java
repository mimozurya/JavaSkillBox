public class Player {
    private Variant variant;
    private String name;

    public Player (Variant variant, String name) {
        this.variant = variant;
        this.name = name;
    }

    public Player () {
        this.variant = Variant.getRandom();
        this.name = "bot";
    }

    public String whoWins(Player user) {
        if (this.variant == user.variant) {
            return "Ничья";
        }
        String winMessage = "Победил игрок с именем: ";

        switch (user.variant) {
            case ROCK:
                return winMessage + (this.variant == Variant.PAPER ? this.name : user.name);
            case PAPER:
                return winMessage + (this.variant == Variant.SCISSORS ? this.name : user.name);
            default:
                return winMessage + (this.variant == Variant.ROCK ? this.name : user.name);
        }
    }
}
