import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Mayo Nakajo
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();

        // cannot subtract if number will be negative, disable -
        if (top.compareTo(bottom) >= 0) {
            view.updateSubtractAllowed(true);
        } else {
            view.updateSubtractAllowed(false);
        }
        // cannot divide by 0, disable /
        if (!bottom.isZero()) {
            view.updateDivideAllowed(true);
        } else {
            view.updateDivideAllowed(false);
        }
        // could result in stack overflow
        if (bottom.compareTo(INT_LIMIT) <= 0) {
            view.updatePowerAllowed(true);
        } else {
            view.updatePowerAllowed(false);
        }
        // could result in stack overflow, root has to be at least 2
        if (bottom.compareTo(INT_LIMIT) <= 0 && bottom.compareTo(TWO) >= 0) {
            view.updateRootAllowed(true);
        } else {
            view.updateRootAllowed(false);
        }

        view.updateTopDisplay(top);
        view.updateBottomDisplay(bottom);
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = bottom.newInstance();

        temp.transferFrom(bottom);
        bottom.transferFrom(top);
        top.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        // sets top to entered number, bottom displays 0
        top.transferFrom(bottom);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        top.add(bottom);
        // displays result on bottom screen, sets top to 0
        bottom.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSubtractEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        top.subtract(bottom);
        // displays bottom number subtracted from top number on  on bottom
        // screen, and sets top to 0
        bottom.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processMultiplyEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        top.multiply(bottom);
        // displays top and bottom number multiplied on the bottom screen, sets
        // top screen to 0
        bottom.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processDivideEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        top.divide(bottom);
        // divides top number by bottom number, displays result on bottom screen
        // and seats top screen to 0
        bottom.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        int bottomInt = bottom.toInt();
        top.power(bottomInt);
        // top number raised to the bottom number, displays result on bottom
        // screen and sets top to 0
        bottom.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        int bottomInt = bottom.toInt();
        top.root(bottomInt);
        // top number to the root of bottom number, displays result on the
        // bottom screen and sets top to 0
        bottom.transferFrom(top);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {
        // to enter a multi-digit number, multiply current number by 10 and add
        // digit
        this.model.bottom().multiplyBy10(digit);
        updateViewToMatchModel(this.model, this.view);

    }

}
