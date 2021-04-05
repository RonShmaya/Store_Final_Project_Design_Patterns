package Commands;

import Model.MyMap.eSortPick;
import View.ViewOption2SortOptions;

public class SendSortPickCommand implements Command {
	private ViewOption2SortOptions op;

	public SendSortPickCommand(ViewOption2SortOptions op) {
		this.op = op;
	}

	@Override
	public void execute() {
		if (op.gettGroup().getSelectedToggle() == null) // if user execute without pick
			op.getMsgUser().setText("Must Pick One Of The Options");

		else { // send model which way to sort
			if (op.getBtn()[0].isSelected())
				op.mainViewUpdateModelPickSort(eSortPick.SortByAlphabeticAscending);

			else if (op.getBtn()[1].isSelected())
				op.mainViewUpdateModelPickSort(eSortPick.SortByAlphabeticDescending);
			else
				op.mainViewUpdateModelPickSort(eSortPick.SortByEntryorder);
		}

	}

}
