package org.smartregister.pathzeir.fragment;

import android.os.Bundle;

import org.smartregister.child.fragment.BaseChildRegistrationDataFragment;
import org.smartregister.pathzeir.R;
import org.smartregister.pathzeir.util.AppConstants;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ndegwamartin on 2019-05-30.
 */
public class ChildRegistrationDataFragment extends BaseChildRegistrationDataFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fieldNameAliasMap = new HashMap<String, String>() {
            {
                put("mother_guardian_number", "mother_phone_number");
            }
        };
    }

    @Override
    public String getRegistrationForm() {
        return AppConstants.JsonForm.CHILD_ENROLLMENT;
    }


    @Override
    protected List<String> addUnFormattedNumberFields(String... key) {
        return Collections.singletonList("mother_guardian_number");
    }

    @Override
    protected Map<String, String> getDataRowLabelResourceIds() {
        fieldNameResourceMap = new HashMap<String, Integer>() {
            {
                put("Date_Birth", R.string.child_DOB);
                put("mother_guardian_date_birth", R.string.mother_dob);
                put("birth_facility_name", R.string.birth_health_facility);
            }
        };
        return super.getDataRowLabelResourceIds();
    }
}
