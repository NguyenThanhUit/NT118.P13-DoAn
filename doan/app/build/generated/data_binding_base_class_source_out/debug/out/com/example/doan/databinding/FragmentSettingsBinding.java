// Generated by view binder compiler. Do not edit!
package com.example.doan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.doan.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSettingsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnCreateaccount;

  @NonNull
  public final Button btnLogout;

  @NonNull
  public final Button btnNotifications;

  @NonNull
  public final MaterialCardView createAccountCard;

  @NonNull
  public final LinearLayout header;

  @NonNull
  public final MaterialCardView languageCard;

  @NonNull
  public final Spinner languageSpinner;

  @NonNull
  public final Switch nightModeSwitch;

  @NonNull
  public final MaterialCardView notificationsCard;

  private FragmentSettingsBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnCreateaccount, @NonNull Button btnLogout, @NonNull Button btnNotifications,
      @NonNull MaterialCardView createAccountCard, @NonNull LinearLayout header,
      @NonNull MaterialCardView languageCard, @NonNull Spinner languageSpinner,
      @NonNull Switch nightModeSwitch, @NonNull MaterialCardView notificationsCard) {
    this.rootView = rootView;
    this.btnCreateaccount = btnCreateaccount;
    this.btnLogout = btnLogout;
    this.btnNotifications = btnNotifications;
    this.createAccountCard = createAccountCard;
    this.header = header;
    this.languageCard = languageCard;
    this.languageSpinner = languageSpinner;
    this.nightModeSwitch = nightModeSwitch;
    this.notificationsCard = notificationsCard;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSettingsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSettingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_settings, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSettingsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_createaccount;
      Button btnCreateaccount = ViewBindings.findChildViewById(rootView, id);
      if (btnCreateaccount == null) {
        break missingId;
      }

      id = R.id.btn_logout;
      Button btnLogout = ViewBindings.findChildViewById(rootView, id);
      if (btnLogout == null) {
        break missingId;
      }

      id = R.id.btn_notifications;
      Button btnNotifications = ViewBindings.findChildViewById(rootView, id);
      if (btnNotifications == null) {
        break missingId;
      }

      id = R.id.create_account_card;
      MaterialCardView createAccountCard = ViewBindings.findChildViewById(rootView, id);
      if (createAccountCard == null) {
        break missingId;
      }

      id = R.id.header;
      LinearLayout header = ViewBindings.findChildViewById(rootView, id);
      if (header == null) {
        break missingId;
      }

      id = R.id.language_card;
      MaterialCardView languageCard = ViewBindings.findChildViewById(rootView, id);
      if (languageCard == null) {
        break missingId;
      }

      id = R.id.language_spinner;
      Spinner languageSpinner = ViewBindings.findChildViewById(rootView, id);
      if (languageSpinner == null) {
        break missingId;
      }

      id = R.id.night_mode_switch;
      Switch nightModeSwitch = ViewBindings.findChildViewById(rootView, id);
      if (nightModeSwitch == null) {
        break missingId;
      }

      id = R.id.notifications_card;
      MaterialCardView notificationsCard = ViewBindings.findChildViewById(rootView, id);
      if (notificationsCard == null) {
        break missingId;
      }

      return new FragmentSettingsBinding((ConstraintLayout) rootView, btnCreateaccount, btnLogout,
          btnNotifications, createAccountCard, header, languageCard, languageSpinner,
          nightModeSwitch, notificationsCard);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
