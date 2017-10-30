package home.rxjavatest;

import home.rxjavatest.rest.PBBransches;

/**
 * Created by Burge on 27.10.2017.
 */

public class BranchListner {
    PBBransches pbBransch;

    public BranchListner(PBBransches pbBransch) {
        this.pbBransch = pbBransch;
    }

    public PBBransches getPbBransch() {
        return pbBransch;
    }

    public void setPbBransch(PBBransches pbBransch) {
        this.pbBransch = pbBransch;
    }
}
