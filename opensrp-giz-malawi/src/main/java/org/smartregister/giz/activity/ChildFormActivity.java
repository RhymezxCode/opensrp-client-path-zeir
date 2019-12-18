package org.smartregister.giz.activity;

import com.vijay.jsonwizard.constants.JsonFormConstants;

import org.smartregister.child.activity.BaseChildFormActivity;
import org.smartregister.giz.fragment.ChildFormFragment;

public class ChildFormActivity extends BaseChildFormActivity {
    @Override
    public void initializeFormFragment() {
        initializeFormFragmentCore();
    }

    protected void initializeFormFragmentCore() {
        ChildFormFragment childFormFragment = (ChildFormFragment) ChildFormFragment.getFormFragment(JsonFormConstants.FIRST_STEP_NAME);
        getSupportFragmentManager().beginTransaction().add(com.vijay.jsonwizard.R.id.container, childFormFragment).commit();
    }

}
