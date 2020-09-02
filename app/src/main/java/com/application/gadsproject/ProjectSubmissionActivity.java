package com.application.gadsproject;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.application.gadsproject.models.User;

import com.application.gadsproject.services.ServiceBuilder;
import com.application.gadsproject.services.SubmitProjectService;

import java.util.Timer;
import java.util.TimerTask;

public class ProjectSubmissionActivity extends AppCompatActivity {

    EditText firstName, lastName, email, githubLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submittion);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        githubLink = findViewById(R.id.githubLink);

        final ImageView imageView = findViewById(R.id.close);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FirstName = firstName.getText().toString().trim();
                String LastName = lastName.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String GithubLink = githubLink.getText().toString().trim();

                if (FirstName.isEmpty()){
                    firstName.setError("Field is required");
                }else if (LastName.isEmpty()){
                    lastName.setError("Field is required");
                }else if (Email.isEmpty()){
                    email.setError("Field is required");
                }else if (GithubLink.isEmpty()){
                    githubLink.setError("Field is required");
                }else {
                    final Dialog dialog = new Dialog(ProjectSubmissionActivity.this);
                    dialog.setContentView(R.layout.are_you_sure_dialog);
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    Button button = dialog.findViewById(R.id.button_yes);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Submit();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                    ImageView imageView1 = dialog.findViewById(R.id.close);
                    imageView1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                }

            }
        });
    }

    private void Submit() {

        String FirstName = firstName.getText().toString().trim();
        String LastName = lastName.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String GithubLink = githubLink.getText().toString().trim();

        User user = new User();
        user.setFirstName(FirstName);
        user.setLastName(LastName);
        user.setEmail(Email);
        user.setGithubLink(GithubLink);

        SubmitProjectService service = ServiceBuilder.buildService(SubmitProjectService.class);
        Call<User> userCall = service.submitProject(
              FirstName, LastName, Email, GithubLink
        );

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                final Dialog dialog = new Dialog(ProjectSubmissionActivity.this);
                dialog.setContentView(R.layout.successful_submission_dialog);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                final Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        dialog.dismiss();
                        t.cancel();
                    }
                }, 2000);

                dialog.show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                final Dialog dialog = new Dialog(ProjectSubmissionActivity.this);
                dialog.setContentView(R.layout.failed_submission_dialog);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        dialog.dismiss();
                        timer.cancel();
                    }
                }, 2000);

                dialog.show();
            }
        });

    }


}