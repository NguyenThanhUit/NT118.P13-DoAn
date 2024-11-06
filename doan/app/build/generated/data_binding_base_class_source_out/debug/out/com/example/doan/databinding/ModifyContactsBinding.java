// Generated by view binder compiler. Do not edit!
package com.example.doan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.doan.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ModifyContactsBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnUpdate;

  @NonNull
  public final Button btnedit;

  @NonNull
  public final EditText editTextAddress;

  @NonNull
  public final EditText editTextEmail;

  @NonNull
  public final EditText editTextMAKH;

  @NonNull
  public final EditText editTextName;

  @NonNull
  public final EditText editTextPhone;

  @NonNull
  public final ImageButton ibtnBack;

  @NonNull
  public final ImageButton imageButton2;

  @NonNull
  public final TextView textViewMAKH;

  @NonNull
  public final TextView textViewName;

  @NonNull
  public final TextView tvAddress;

  @NonNull
  public final TextView tvEmail;

  @NonNull
  public final TextView tvPhone;

  private ModifyContactsBinding(@NonNull LinearLayout rootView, @NonNull Button btnUpdate,
      @NonNull Button btnedit, @NonNull EditText editTextAddress, @NonNull EditText editTextEmail,
      @NonNull EditText editTextMAKH, @NonNull EditText editTextName,
      @NonNull EditText editTextPhone, @NonNull ImageButton ibtnBack,
      @NonNull ImageButton imageButton2, @NonNull TextView textViewMAKH,
      @NonNull TextView textViewName, @NonNull TextView tvAddress, @NonNull TextView tvEmail,
      @NonNull TextView tvPhone) {
    this.rootView = rootView;
    this.btnUpdate = btnUpdate;
    this.btnedit = btnedit;
    this.editTextAddress = editTextAddress;
    this.editTextEmail = editTextEmail;
    this.editTextMAKH = editTextMAKH;
    this.editTextName = editTextName;
    this.editTextPhone = editTextPhone;
    this.ibtnBack = ibtnBack;
    this.imageButton2 = imageButton2;
    this.textViewMAKH = textViewMAKH;
    this.textViewName = textViewName;
    this.tvAddress = tvAddress;
    this.tvEmail = tvEmail;
    this.tvPhone = tvPhone;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ModifyContactsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ModifyContactsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.modify_contacts, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ModifyContactsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnUpdate;
      Button btnUpdate = ViewBindings.findChildViewById(rootView, id);
      if (btnUpdate == null) {
        break missingId;
      }

      id = R.id.btnedit;
      Button btnedit = ViewBindings.findChildViewById(rootView, id);
      if (btnedit == null) {
        break missingId;
      }

      id = R.id.editTextAddress;
      EditText editTextAddress = ViewBindings.findChildViewById(rootView, id);
      if (editTextAddress == null) {
        break missingId;
      }

      id = R.id.editTextEmail;
      EditText editTextEmail = ViewBindings.findChildViewById(rootView, id);
      if (editTextEmail == null) {
        break missingId;
      }

      id = R.id.editTextMAKH;
      EditText editTextMAKH = ViewBindings.findChildViewById(rootView, id);
      if (editTextMAKH == null) {
        break missingId;
      }

      id = R.id.editTextName;
      EditText editTextName = ViewBindings.findChildViewById(rootView, id);
      if (editTextName == null) {
        break missingId;
      }

      id = R.id.editTextPhone;
      EditText editTextPhone = ViewBindings.findChildViewById(rootView, id);
      if (editTextPhone == null) {
        break missingId;
      }

      id = R.id.ibtnBack;
      ImageButton ibtnBack = ViewBindings.findChildViewById(rootView, id);
      if (ibtnBack == null) {
        break missingId;
      }

      id = R.id.imageButton2;
      ImageButton imageButton2 = ViewBindings.findChildViewById(rootView, id);
      if (imageButton2 == null) {
        break missingId;
      }

      id = R.id.textViewMAKH;
      TextView textViewMAKH = ViewBindings.findChildViewById(rootView, id);
      if (textViewMAKH == null) {
        break missingId;
      }

      id = R.id.textViewName;
      TextView textViewName = ViewBindings.findChildViewById(rootView, id);
      if (textViewName == null) {
        break missingId;
      }

      id = R.id.tvAddress;
      TextView tvAddress = ViewBindings.findChildViewById(rootView, id);
      if (tvAddress == null) {
        break missingId;
      }

      id = R.id.tvEmail;
      TextView tvEmail = ViewBindings.findChildViewById(rootView, id);
      if (tvEmail == null) {
        break missingId;
      }

      id = R.id.tvPhone;
      TextView tvPhone = ViewBindings.findChildViewById(rootView, id);
      if (tvPhone == null) {
        break missingId;
      }

      return new ModifyContactsBinding((LinearLayout) rootView, btnUpdate, btnedit, editTextAddress,
          editTextEmail, editTextMAKH, editTextName, editTextPhone, ibtnBack, imageButton2,
          textViewMAKH, textViewName, tvAddress, tvEmail, tvPhone);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}