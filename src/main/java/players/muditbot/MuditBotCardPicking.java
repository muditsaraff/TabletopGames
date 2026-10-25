package players.muditbot;

import core.AbstractGameState;
import core.actions.AbstractAction;
import core.interfaces.IStateHeuristic;
import players.basicMCTS.BasicMCTSParams;
import players.simple.RandomPlayer;

import java.util.List;

/**
 * MuditBot variant with draft-aware rollouts and known-card estimates.
 */
public class MuditBotCardPicking extends MuditBot {

    public MuditBotCardPicking() {
        super();
        useCardPickingStrategy();
    }

    public MuditBotCardPicking(long seed) {
        super(seed);
        useCardPickingStrategy();
    }

    public MuditBotCardPicking(BasicMCTSParams params) {
        super(params);
        useCardPickingStrategy();
    }

    private void useCardPickingStrategy() {
        getParameters().setParameterValue("heuristic",
                (IStateHeuristic) MuditBot::evaluateCardPickingState);
        getParameters().heuristic = MuditBot::evaluateCardPickingState;
        setName("muditbot_cardpicking");
    }

    @Override
    public AbstractAction getRolloutAction(AbstractGameState state, List<AbstractAction> actions,
                                           RandomPlayer randomPlayer) {
        return selectCardPickingRolloutAction(state, actions, randomPlayer);
    }

    @Override
    public MuditBotCardPicking copy() {
        return new MuditBotCardPicking((BasicMCTSParams) getParameters().copy());
    }
}
