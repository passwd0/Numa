package com.numa.emma.numa.view;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.numa.emma.numa.R;
import com.numa.emma.numa.classes.Controller;

public class Program extends Fragment {
    private Controller controller;
    public static com.numa.emma.numa.adapters.Program programmerListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ConstraintLayout view = (ConstraintLayout) inflater.inflate(R.layout.program, container, false);

        controller = (Controller) getActivity().getApplicationContext();

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        programmerListAdapter = new com.numa.emma.numa.adapters.Program(getActivity(), controller);
        recyclerView.setAdapter(programmerListAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                getResources().getConfiguration().orientation);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ImageButton addButton = view.findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("program", new com.numa.emma.numa.classes.Program());
                ProgramAdd fragment = new ProgramAdd();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).addToBackStack(null).commit();
            }
        });

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        programmerListAdapter.notifyDataSetChanged();
        getActivity().setTitle("Programma");
    }
}
