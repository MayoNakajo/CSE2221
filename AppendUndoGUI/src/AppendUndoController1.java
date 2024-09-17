/**
 * Controller class.
 *
 * @author Bruce W. Weide
 * @author Paolo Bucci
 */
public final class AppendUndoController1 implements AppendUndoController {

    /**
     * Model object.
     */
    private final AppendUndoModel model;

    /**
     * View object.
     */
    private final AppendUndoView view;

    /**
     * Updates view to display model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     */
    private static void updateViewToMatchModel(AppendUndoModel model,
            AppendUndoView view) {
        /*
         * Get model info
         */
        String input = model.input();
        String output = model.output().toString();

        /*
         * Update view to reflect changes in model
         */

        if (output.length() >= 1) {
            view.updateUndoAllowed(true);
        } else {
            view.updateUndoAllowed(false);
        }

        view.updateInputDisplay(input);
        view.updateOutputDisplay(output);

    }

    /**
     * Constructor; connects {@code this} to the model and view it coordinates.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public AppendUndoController1(AppendUndoModel model, AppendUndoView view) {
        this.model = model;
        this.view = view;
        /*
         * Update view to reflect initial value of model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to reset model.
     *
     * @updates {@code this.model, this.view}
     * @ensures <pre>
     * {@code this.model.input = ""  and
     * this.model.output = <>  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    @Override
    public void processResetEvent() {
        String empty = "";
        this.model.setInput(empty);
        this.model.output().clear();
        this.model.output().push(empty);
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to append to output.
     *
     * @param input
     *            string to be appended
     *
     * @updates {@code this.model, this.view}
     * @ensures <pre>
     * {@code this.model.input = ""  and
     * this.model.output =  <input> * #this.model.output  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    @Override
    public void processAppendEvent(String input) {
        String empty = "";
        String add = this.model.output().top();
        this.model.setInput(empty);
        this.model.output().push(input);
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes event to undo last append to output.
     *
     * @updates {@code this.model, this.view}
     * @requires <pre>
     * {@code this.model.output /= <>}
     * </pre>
     * @ensures <pre>
     * {@code #this.model.output = <this.model.input> * this.model.output  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    @Override
    public void processUndoEvent() {
        String undoStr = this.model.output().pop();
        String str = this.model.input();
        this.model.setInput(str + undoStr);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processRedoEvent() {
        this.model.output().push(this.model.input());
        this.model.setInput("");
        updateViewToMatchModel(this.model, this.view);
    }

}
