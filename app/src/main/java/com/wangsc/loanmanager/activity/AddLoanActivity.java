package com.wangsc.loanmanager.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.wangsc.loanmanager.R;
import com.wangsc.loanmanager.fragment.ActionBarFragment;
import com.wangsc.loanmanager.helper.DateTime;
import com.wangsc.loanmanager.helper.NumberToCN;
import com.wangsc.loanmanager.helper._Helper;
import com.wangsc.loanmanager.helper._String;
import com.wangsc.loanmanager.model.Address;
import com.wangsc.loanmanager.model.Borrower;
import com.wangsc.loanmanager.model.Loan;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class AddLoanActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {


    // 视图
    private AutoCompleteTextView autoCompleteName, autoCompleteProvince, autoCompleteCity, autoCompleteCounty, autoCompleteTown, autoCompleteVillage;
    private EditText editTextType, editTextDate, editTextAmount, editTextLife, editTextIdentity, editTextPhone;
    private TextView textViewAmountCN;

    private Loan loan;
    private Borrower borrower;
    private Address address;
    private UUID loanGroupId;

    public static String PARAM_LOAN_GROUP_ID = "LOAN_GROUP_ID";
    private static String moneyText = "";


    private static final int REQUEST_READ_CONTACTS = 114;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_loan);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, ActionBarFragment.newInstance()).commit();
            getSupportActionBar().hide();

            loanGroupId = UUID.fromString(getIntent().getStringExtra(PARAM_LOAN_GROUP_ID));

            borrower = new Borrower(UUID.randomUUID());
            loan = new Loan(UUID.randomUUID());
            loan.setBorrowerId(borrower.getId());
            address = new Address(UUID.randomUUID());

            textViewAmountCN = (TextView) findViewById(R.id.textView_money_cn);
            editTextAmount = (EditText) findViewById(R.id.amount);
            editTextAmount.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    }
                    return false;
                }
            });
            editTextAmount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    String text = editTextAmount.getText().toString();
                    if (!moneyText.equals(text)) {
                        String word = _String.addComma(editTextAmount);
                        moneyText = word;
                        editTextAmount.setText(word);
                        editTextAmount.setSelection(word.length());
                    }


                    if (text.isEmpty()) {
                        textViewAmountCN.setVisibility(View.GONE);
                        textViewAmountCN.setText("");
                    } else {
                        textViewAmountCN.setVisibility(View.VISIBLE);
                        textViewAmountCN.setText(NumberToCN.number2CNMontrayUnit(BigDecimal.valueOf(Double.parseDouble(editTextAmount.getText().toString().replace(",","")))));
                    }
                }
            });

            editTextLife = (EditText) findViewById(R.id.life);
//            editTextLife.setFocusable(false);
            editTextLife.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        setLifeDialog(12);
                    }
                    return true;
                }
            });

            editTextLife.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            editTextDate = (EditText) findViewById(R.id.date);
//            editTextDate.setFocusable(false);
            editTextDate.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        setDateDialog(new DateTime());
                    }
                    return true;
                }
            });
//            // Set up the login form.
//            mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
//            populateAutoComplete();
//
//            mPasswordView = (EditText) findViewById(R.id.password);
//            mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                @Override
//                public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                    if (id == R.id.login || id == EditorInfo.IME_NULL) {
//                        attemptCreate();
//                        return true;
//                    }
//                    return false;
//                }
//            });
//
//            Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
//            mEmailSignInButton.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    attemptCreate();
//                }
//            });
//
//            mLoginFormView = findViewById(R.id.login_form);
//            mProgressView = findViewById(R.id.login_progress);
        } catch (Exception e) {
            _Helper.printException(this, e);
        }
    }

    private String money2Chinese(double money) {
        String result = "";

        return result;
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(editTextDate, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * 验证数据，并在数据全部有效的前提下，将数据存入数据库。
     */
    private void attemptCreate() {

//        // Reset errors.
//        mEmailView.setError(null);
//        mPasswordView.setError(null);
//
//        // TODO: 2017/5/16 获取数据字段内容。
//        String email = mEmailView.getText().toString();
//        String password = mPasswordView.getText().toString();
//
        boolean cancel = false;
        View focusView = null;
//
//        // Check for a valid password, if the user entered one.
//        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
//            mPasswordView.setError(getString(R.string.error_invalid_password));
//            focusView = mPasswordView;
//            cancel = true;
//        }
//
//        // Check for a valid email address.
//        if (TextUtils.isEmpty(email)) {
//            mEmailView.setError(getString(R.string.error_field_required));
//            focusView = mEmailView;
//            cancel = true;
//        } else if (!isEmailValid(email)) {
//            mEmailView.setError(getString(R.string.error_invalid_email));
//            focusView = mEmailView;
//            cancel = true;
//        }

        if (cancel) {
            // 经过验证之后，字段数据有错误，将定位到有错误的字段输入框。
            focusView.requestFocus();
        } else {
            // TODO: 2017/5/16 所有字段验证完成，将数据存入数据库。
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addNameToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addNameToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(AddLoanActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        autoCompleteName.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


    public void setDateDialog(DateTime dateTime) {

        try {
            View view = View.inflate(this, R.layout.inflate_dialog_date_picker, null);
            final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
            dialog.setTitle("设定借款日期");

            final int year = dateTime.getYear();
            int month = dateTime.getMonth();
            int day = dateTime.getDay();

            String[] yearNumbers = new String[11];
            for (int i = year - 10; i <= year; i++) {
                yearNumbers[i - year + 10] = i + "年";
            }
            String[] monthNumbers = new String[12];
            for (int i = 0; i < 12; i++) {
                monthNumbers[i] = i + 1 + "月";
            }
            String[] dayNumbers = new String[31];
            for (int i = 0; i < 31; i++) {
                dayNumbers[i] = i + 1 + "日";
            }
            final NumberPicker npYear = (NumberPicker) view.findViewById(R.id.npYear);
            final NumberPicker npMonth = (NumberPicker) view.findViewById(R.id.npMonth);
            final NumberPicker npDay = (NumberPicker) view.findViewById(R.id.npDay);
            npYear.setMinValue(year - 10);
            npYear.setMaxValue(year);
            npYear.setValue(year);
            npYear.setDisplayedValues(yearNumbers);
            npYear.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 禁止对话框打开后数字选择框被选中
            npMonth.setMinValue(1);
            npMonth.setMaxValue(12);
            npMonth.setDisplayedValues(monthNumbers);
            npMonth.setValue(month + 1);
            npMonth.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 禁止对话框打开后数字选择框被选中
            npDay.setMinValue(1);
            npDay.setMaxValue(31);
            npDay.setDisplayedValues(dayNumbers);
            npDay.setValue(day);
            npDay.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 禁止对话框打开后数字选择框被选中

            npMonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    DateTime selected = new DateTime(npYear.getValue(), npMonth.getValue() - 1, 1);
                    int max = selected.getActualMaximum(Calendar.DAY_OF_MONTH);

                    int day = npDay.getValue();
                    npDay.setMaxValue(max);
                    if (day > max) {
                        npDay.setValue(1);
                    } else {
                        npDay.setValue(day);
                    }
                }
            });

            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        int y = npYear.getValue();
                        int m = npMonth.getValue() - 1;
                        int d = npDay.getValue();
                        DateTime selectedDateTime = new DateTime(y, m, d, 0, 0, 0);
                        loan.setDate(selectedDateTime);
                        editTextDate.setText(selectedDateTime.toShortDateString());
                        dialog.dismiss();
                    } catch (Exception e) {
                        _Helper.printException(AddLoanActivity.this, e);
                    }
                }
            });
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        _Helper.printException(AddLoanActivity.this, e);
                    }
                }
            });
            dialog.show();
        } catch (Exception e) {
            _Helper.printException(AddLoanActivity.this, e);
        }
    }

    public void setLifeDialog(int totalMonth) {

        try {
            View view = View.inflate(this, R.layout.inflate_dialog_date_picker, null);
            final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
            dialog.setTitle("设定借款期限");

            final int year = totalMonth / 12;
            int month = totalMonth % 12;

            String[] yearNumbers = new String[5];
            for (int i = 0; i < 5; i++) {
                yearNumbers[i] = i + 1 + "年";
            }
            String[] monthNumbers = new String[12];
            for (int i = 0; i < 12; i++) {
                monthNumbers[i] = i + "个月";
            }
            final NumberPicker npYear = (NumberPicker) view.findViewById(R.id.npYear);
            final NumberPicker npMonth = (NumberPicker) view.findViewById(R.id.npMonth);
            final NumberPicker npDay = (NumberPicker) view.findViewById(R.id.npDay);
            npYear.setMinValue(1);
            npYear.setMaxValue(5);
            npYear.setValue(year);
            npYear.setDisplayedValues(yearNumbers);
            npYear.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 禁止对话框打开后数字选择框被选中
            npMonth.setMinValue(0);
            npMonth.setMaxValue(11);
            npMonth.setDisplayedValues(monthNumbers);
            npMonth.setValue(month);
            npMonth.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 禁止对话框打开后数字选择框被选中
            npDay.setVisibility(View.GONE);

            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        int y = npYear.getValue();
                        int m = npMonth.getValue();
                        loan.setLife(y * 12 + m);
                        editTextLife.setText(lifeInt2String(y, m));
                        dialog.dismiss();
                    } catch (Exception e) {
                        _Helper.printException(AddLoanActivity.this, e);
                    }
                }
            });
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        _Helper.printException(AddLoanActivity.this, e);
                    }
                }
            });
            dialog.show();
        } catch (Exception e) {
            _Helper.printException(AddLoanActivity.this, e);
        }
    }

    private String lifeInt2String(int year, int month) {
        String result = "";
        if (year > 0) {
            result += year + "年";
        }
        if (month > 0) {
            result += month + "个月";
        }
        return result;
    }
}

