package controller;

import model.Trainer;

@FunctionalInterface
public interface TrainerAction {
	void execute(Trainer trainer);
}
