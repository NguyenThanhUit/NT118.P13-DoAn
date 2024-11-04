// Generated by view binder compiler. Do not edit!
package com.example.doan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
  public final EditText editTextAddress;

  @NonNull
  public final EditText editTextMAKH;

  @NonNull
  public final EditText editTextName;

  @NonNull
  public final EditText editTextPhone;

  private ModifyContactsBinding(@NonNull LinearLayout rootView, @NonNull Button btnUpdate,
      @NonNull EditText editTextAddress, @NonNull EditText editTextMAKH,
      @NonNull EditText editTextName, @NonNull EditText editTextPhone) {
    this.rootView = rootView;
    this.btnUpdate = btnUpdate;
    this.editTextAddress = editTextAddress;
    this.editTextMAKH = editTextMAKH;
    this.editTextName = editTextName;
    this.editTextPhone = editTextPhone;
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

      id = R.id.editTextAddress;
      EditText editTextAddress = ViewBindings.findChildViewById(rootView, id);
      if (editTextAddress == null) {
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

      return new ModifyContactsBinding((LinearLayout) rootView, btnUpdate, editTextAddress,
          editTextMAKH, editTextName, editTextPhone);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
