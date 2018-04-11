package demo.com.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* Created by Lingeshwaran.N on 28-03-2018. */
@SuppressWarnings("unused")
public class GoogleSearchBar extends LinearLayout implements View.OnClickListener,
        View.OnFocusChangeListener, Animation.AnimationListener, TextWatcher,
        TextView.OnEditorActionListener {

    private ICallVoiceCommandListner iVoiceCommandListner;
    private INavigationClickListner iNavigationClickListner;
    private ImageView mNavIcon;
    private TextView mPlaceHolder;
    private EditText mSearchEdit;
    private ImageView mVoiceCloseIcon;
    private DefaultSuggestionsAdapter mSuggestionAdapter;

    public GoogleSearchBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public GoogleSearchBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GoogleSearchBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.google_search_bar, this);
        initialiseView();
    }

    private static final int[] mNavIconArray = {
            R.drawable.ic_dehaze_black_24dp, //0
            R.drawable.ic_arrow_back_black_24dp //1
    };

    private static final int[] mVoiceCloseIconArray = {
            R.drawable.ic_keyboard_voice_black_24dp,  //0
            R.drawable.ic_close_black_24dp //1
    };

    private void initialiseView() {
        mNavIcon = findViewById(R.id.mt_nav_icon);
        mPlaceHolder = findViewById(R.id.mt_text_place_holder);
        mSearchEdit = findViewById(R.id.mt_edit_text_place_holder);
        mVoiceCloseIcon = findViewById(R.id.mt_voice_icon);

        mNavIcon.setTag(mNavIconArray[0]);
        mNavIcon.setOnClickListener(this);

        mPlaceHolder.setOnClickListener(this);

        mSearchEdit.setOnFocusChangeListener(this);
        mSearchEdit.addTextChangedListener(this);
        mSearchEdit.setOnEditorActionListener(this);

        mVoiceCloseIcon.setTag(mVoiceCloseIconArray[0]);
        mVoiceCloseIcon.setOnClickListener(this);

        LinearLayout mSuggestionViewLayout = findViewById(R.id.mt_suggestionView);
        RecyclerView mSuggestionList = findViewById(R.id.mt_suggestionList);
        String[] data = new String[]{"One", "Two", "Three"};
        mSuggestionAdapter = new DefaultSuggestionsAdapter(data);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mSuggestionList.setAdapter(mSuggestionAdapter);
        mSuggestionList.setLayoutManager(mLayoutManager);

    }

    public DefaultSuggestionsAdapter getmSuggestionAdapter() {
        return mSuggestionAdapter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mt_nav_icon:
                rotNavIcon();
                break;
            case R.id.mt_text_place_holder:
                enableSearchHolder();
                break;
            case R.id.mt_voice_icon:
                rotVoiceIcon();
                break;
            default:
                break;
        }
    }

    private void rotNavIcon() {
        switch ((int) getmNavIcon().getTag()) {
            case R.drawable.ic_dehaze_black_24dp:
                if (iNavigationClickListner != null) {
                    iNavigationClickListner.onINavigationClick();
                }
                break;
            case R.drawable.ic_arrow_back_black_24dp:
                animateArrowToNavIcon();
                if ((int) getmVoiceCloseIcon().getTag() == mVoiceCloseIconArray[1]) {
                    animateCloseToVoice();
                }
                break;
        }
    }

    public void getEnableSearchHolder() {
        enableSearchHolder();
    }

    private void enableSearchHolder() {
        animateNavToArrowIcon();
    }

    private void rotVoiceIcon() {
        switch ((int) getmVoiceCloseIcon().getTag()) {
            case R.drawable.ic_keyboard_voice_black_24dp:
                callVoiceCommand();
                break;
            case R.drawable.ic_close_black_24dp:
                animateCloseToVoice();
                getmSearchEdit().getText().clear();
                break;
        }
    }

    public void setOnNavigationClickListener(INavigationClickListner iNavigationClickListner) {
        this.iNavigationClickListner = iNavigationClickListner;
    }

    public void setOnCallVoiceListener(ICallVoiceCommandListner iVoiceCommandListner) {
        this.iVoiceCommandListner = iVoiceCommandListner;
    }

    private void callVoiceCommand() {
        if (iVoiceCommandListner != null) {
            iVoiceCommandListner.onICallVoiceCommand();
        }
    }

    private void animateArrowToNavIcon() {
        final Animation leftToRightRotation = AnimationUtils
                .loadAnimation(getContext(), R.anim.half_left_to_right_rotation);
        leftToRightRotation.setAnimationListener(this);
        mNavIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_dehaze_black_24dp));
        mNavIcon.startAnimation(leftToRightRotation);
        mNavIcon.setTag(mNavIconArray[0]);
    }

    private void animateNavToArrowIcon() {
        final Animation rightToLeftRotation = AnimationUtils
                .loadAnimation(getContext(), R.anim.half_right_to_left_rotation);
        rightToLeftRotation.setAnimationListener(this);
        mNavIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        mNavIcon.startAnimation(rightToLeftRotation);
        mNavIcon.setTag(mNavIconArray[1]);

        if (!getmSearchEdit().getText().toString().trim().isEmpty()) {
            animateVoiceToClose();
        }
    }

    private void animateCloseToVoice() {
        final Animation leftToRightRotation = AnimationUtils
                .loadAnimation(getContext(), R.anim.half_left_to_right_rotation);
        leftToRightRotation.setAnimationListener(this);
        mVoiceCloseIcon
                .setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_voice_black_24dp));
        mVoiceCloseIcon.startAnimation(leftToRightRotation);
        mVoiceCloseIcon.setTag(mVoiceCloseIconArray[0]);
    }

    private void animateVoiceToClose() {
        final Animation rightToLeftRotation = AnimationUtils
                .loadAnimation(getContext(), R.anim.half_right_to_left_rotation);
        rightToLeftRotation.setAnimationListener(this);
        mVoiceCloseIcon
                .setImageDrawable(getResources().getDrawable(R.drawable.ic_close_black_24dp));
        mVoiceCloseIcon.startAnimation(rightToLeftRotation);
        mVoiceCloseIcon.setTag(mVoiceCloseIconArray[1]);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        switch ((int) getmNavIcon().getTag()) {
            case R.drawable.ic_dehaze_black_24dp:
                getmPlaceHolder().setVisibility(View.VISIBLE);
                getmSearchEdit().setVisibility(View.GONE);
                break;
            case R.drawable.ic_arrow_back_black_24dp:
                getmPlaceHolder().setVisibility(View.GONE);
                getmSearchEdit().setVisibility(View.VISIBLE);
                getmSearchEdit().setFocusable(true);
                getmSearchEdit().requestFocus();
                break;
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            if (hasFocus) {
                imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
                showSuggestion(v);
            } else {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                hideSuggestion(v);
            }
        }
    }

    private void hideSuggestion(View v) {
        v.post(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "hideSuggestion");
            }
        });
    }

    private void showSuggestion(View v) {
        v.post(new Runnable() {
            @Override
            public void run() {
                Log.e("TAG", "showSuggestion");
                getmSuggestionAdapter().notifyDataSetChanged();
            }
        });
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            hideKeyboard(v);
            return true;
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (start == 0 && before == 0 && count == 1) {
            animateVoiceToClose();
        } else if (start == 0 && before == 1 && count == 0) {
            animateCloseToVoice();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    public ImageView getmNavIcon() {
        return mNavIcon;
    }

    public ImageView getmVoiceCloseIcon() {
        return mVoiceCloseIcon;
    }

    public TextView getmPlaceHolder() {
        return mPlaceHolder;
    }

    public EditText getmSearchEdit() {
        return mSearchEdit;
    }

    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

}
