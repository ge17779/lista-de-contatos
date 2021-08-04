package br.com.gonzales.listadecontatos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.com.gonzales.listadecontatos.models.User;

public class MainActivity extends AppCompatActivity {

    public ListView usersListView;
    public ArrayList<User> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterAndEdit.class);
                startActivity(intent);
            }
        });

        carregaLista();
    }

    @SuppressLint("Range")
    public void carregaLista(){

        DataBaseController crud = new DataBaseController(getBaseContext());
        Cursor cursor = crud.carregaDados();
        cursor.moveToFirst();

        usersList = new ArrayList<User>();

        while(cursor.moveToNext()){
            User user = new User();
            user.setNameTxt(cursor.getString(cursor.getColumnIndex("nome")));
            user.setPhoneTxt(cursor.getString(cursor.getColumnIndex("telefone")));
            user.setDateOfBirthTxt(cursor.getString(cursor.getColumnIndex("dataNascimento")));
            user.setCepTxt(cursor.getString(cursor.getColumnIndex("cep")));
            user.setEstadoTxt(cursor.getString(cursor.getColumnIndex("estado")));
            user.setCidadeTxt(cursor.getString(cursor.getColumnIndex("cidade")));
            user.setBairroTxt(cursor.getString(cursor.getColumnIndex("bairro")));
            user.setRuaTxt(cursor.getString(cursor.getColumnIndex("rua")));
            user.setNumeroTxt(cursor.getString(cursor.getColumnIndex("numero")));
            usersList.add(user);
        }

        usersListView = findViewById(R.id.user_list_view);

//        ArrayAdapter userAdapter = new ArrayAdapter<User>(this, R.layout.contact_item, R.id.name_user, usersList);
        usersListView.setAdapter(new UserAdapter(getBaseContext(), usersList));
    }

    public class UserAdapter extends BaseAdapter {

        ArrayList<User> userList;
        Context mContext;

        public UserAdapter(@NonNull Context context, ArrayList<User> list) {
            userList = list;
            mContext = context;
        }

        @Override
        public int getCount() {
            return userList.size();
        }

        @Nullable
        @Override
        public User getItem(int position) {
            return userList.get(position);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = null;
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                row = inflater.inflate(R.layout.contact_item, parent,
                        false);
            } else {
                row = convertView;
            }

            User user = userList.get(position);
            TextView name = (TextView) row.findViewById(R.id.name_user);
            name.setText(user.getNameTxt());
            TextView phone = (TextView) row.findViewById(R.id.phone_user);
            phone.setText(user.getPhoneTxt());
            return row;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.carregaLista();
    }
}