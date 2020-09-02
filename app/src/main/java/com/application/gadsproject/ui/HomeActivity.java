package com.application.gadsproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.application.gadsproject.ProjectSubmissionActivity;
import com.application.gadsproject.R;
import com.application.gadsproject.adapters.TabAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBar);

        Button submit = appBarLayout.findViewById(R.id.button_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProjectSubmissionActivity.class);
                startActivity(intent);
            }
        });

        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new LearningLeadersFragment(), getString(R.string.tab_text_1));
        adapter.addFragment(new SkillIQLeadersFragment(), getString(R.string.tab_text_2));


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}