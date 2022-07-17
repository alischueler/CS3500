package cs3500.pyramidsolitaire;

import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.controller.PyramidSolitaireController;
import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * To represrnt a game of pyramid soliatiare. Contains main method to initialize game.
 */
public final class PyramidSolitaire {

  /**
   * Method to initialize arguments and run a game of pyramid solitaire.
   * @param args represent the arguments from command line.
   */
  public static void main(String[] args) {
    String gameType = "";
    int r = 7;
    int d = 3;
    if (args.length == 3) {
      gameType = args[0];
      r = Integer.parseInt(args[1]);
      d = Integer.parseInt(args[2]);
    }
    else if (args.length == 1) {
      gameType = args[0];
    }
    InputStreamReader rd = new InputStreamReader(System.in);
    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(rd, System.out);
    PyramidSolitaireModel<Card> model;
    Random rand = new Random();
    int shuffleInt = rand.nextInt(2);

    boolean shuffle;
    if (shuffleInt == 0) {
      shuffle = true;
    }
    else {
      shuffle = false;
    }
    model = PyramidSolitaireCreator.create(GameType.valueOf(gameType.toUpperCase()));

    controller.playGame(model, model.getDeck(), shuffle, r, d);
  }
}
