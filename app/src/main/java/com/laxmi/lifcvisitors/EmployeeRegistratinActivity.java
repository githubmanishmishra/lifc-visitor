package com.laxmi.lifcvisitors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EmployeeRegistratinActivity extends AppCompatActivity {
    Button btn_otp;
    Spinner department_spinners;
    Spinner branch_spinners;
    Intent intent;
    ArrayList<String> arrDepartment = new ArrayList<>();
    ArrayList<String> arrBranch = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_registratin);
        department_spinners = findViewById(R.id.spinner_department);
        branch_spinners = findViewById(R.id.branch_spinner);
        arrDepartment.add("Accounts & Finance");
        arrDepartment.add("Admin");
        arrDepartment.add("Internal Audit");
        arrDepartment.add("Collection");
        arrDepartment.add("Compliance");
        arrDepartment.add("Credit");
        arrDepartment.add("Digital Marketing");
        arrDepartment.add("Forecloser");
        arrDepartment.add("Human Resource");
        arrDepartment.add("Legal");
        arrDepartment.add("Legal-Technical");
        arrDepartment.add("IT");
        arrDepartment.add("Insurance");
        arrDepartment.add("Learning & Development");
        arrDepartment.add("Operations");
        arrDepartment.add("Sales");
        arrDepartment.add("Store");
        arrDepartment.add("MD Office");
        ArrayAdapter<String> springAdapter_departMent = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, arrDepartment);
        department_spinners.setAdapter((springAdapter_departMent));
        arrBranch.add("BIKANER");
        arrBranch.add("BIKANER-2");
        arrBranch.add("LUNKARANSAR");
        arrBranch.add("NAGOUR");
        arrBranch.add("NOKHA");
        arrBranch.add("Shri Dungargarh");
        arrBranch.add("ASIND");
        arrBranch.add("BHILWARA");
        arrBranch.add("CHITTORGARH");
        arrBranch.add("CHURU");
        arrBranch.add("FATEHPUR");
        arrBranch.add("RATANGARH (RAJ)");
        arrBranch.add("SARDARSAHAR (CHURU)");
        arrBranch.add("TARANAGAR");
        arrBranch.add("BASSI");
        arrBranch.add("DAUSA");
        arrBranch.add("AJMER");
        arrBranch.add("BEAWAR");
        arrBranch.add("BHANKROTA (JAIPUR)");
        arrBranch.add("BIJAYNAGAR (AJMER)");
        arrBranch.add("DEGANA");
        arrBranch.add("DUDU");
        arrBranch.add("KISHANGARH-AJMER");
        arrBranch.add("MALPURA (TONK)");
        arrBranch.add("PALI");
        arrBranch.add("PARBATSAR");
        arrBranch.add("PHAGI");
        arrBranch.add("SOJAT CITY");
        arrBranch.add("CHOMU");
        arrBranch.add("GOPINATH MARG (JAIPUR)");
        arrBranch.add("HARMADA");
        arrBranch.add("JHOTWARA");
        arrBranch.add("MANSAROVAR (JAIPUR)");
        arrBranch.add("SANGANER");
        arrBranch.add("AGRA ROAD");
        arrBranch.add("JAIPUR HO");
        arrBranch.add("MAKRANA (NAGOUR)");
        arrBranch.add("BALOTRA");
        arrBranch.add("JODHPUR");
        arrBranch.add("OSIAN (JODHPUR)");
        arrBranch.add("PHALODI");
        arrBranch.add("PIPARCITY (JODHPUR)");
        arrBranch.add("BARAN");
        arrBranch.add("BUNDI");
        arrBranch.add("DEOLI (TONK)");
        arrBranch.add("JHALAWAR");
        arrBranch.add("KEKRI");
        arrBranch.add("KOTA");
        arrBranch.add("KANWAT");
        arrBranch.add("KHATUSHYAMJI");
        arrBranch.add("KHETRI (JHUNJHUNU)");
        arrBranch.add("NEEM KA THANA");
        arrBranch.add("REENGUS");
        arrBranch.add("UDAIPURWATI");
        arrBranch.add("BAGRU (JAIPUR)");
        arrBranch.add("DANTARAMGARH");
        arrBranch.add("JOBNER");
        arrBranch.add("KISHANGARH RENWAL");
        arrBranch.add("KUCHAMAN-CITY");
        arrBranch.add("NAWA");
        arrBranch.add("PHULERA");
        arrBranch.add("AJEETGARH");
        arrBranch.add("BANSURE");
        arrBranch.add("BEHROR (ALWAR)");
        arrBranch.add("KOTPUTALI");
        arrBranch.add("PAWTA (KOTPUTALI)");
        arrBranch.add("SHAHPURA");
        arrBranch.add("CHIRAWA (JHUNJHUNU)");
        arrBranch.add("JHUNJHUNU");
        arrBranch.add("LAXMANGARH");
        arrBranch.add("NAWALGARH");
        arrBranch.add("SIKAR");
        arrBranch.add("SUJANGARH (RAJ)");
        arrBranch.add("BHADRA");
        arrBranch.add("HANUMANGARH");
        arrBranch.add("NOHAR");
        arrBranch.add("RAWATSAR");
        arrBranch.add("SRI GANGANAGAR");
        arrBranch.add("SURATGARH");
        arrBranch.add("ABU ROAD");
        arrBranch.add("BHINMAL");
        arrBranch.add("JALORE");
        arrBranch.add("SIROHI");
        arrBranch.add("SUMERPUR (PALI)");
        arrBranch.add("CHAKSU");
        arrBranch.add("NIWAI");
        arrBranch.add("TONK");
        arrBranch.add("RAJSAMAND");
        arrBranch.add("UDAIPUR");
        arrBranch.add("AHEMADABAD (GUJARAT)");
        arrBranch.add("ANAND (GUJARAT)");
        arrBranch.add("BARODA (GUJARAT)");
        arrBranch.add("BODELI (GUJARAT)");
        arrBranch.add("GODHRA (GUJARAT)");
        arrBranch.add("HALOL (GUJARAT)");
        arrBranch.add("KAPADVANJ (GUJARAT)");
        arrBranch.add("MODASA (GUJARAT)");
        arrBranch.add("HIMMATNAGAR (GUJARAT)");
        arrBranch.add("KHEDBHRHMA (GUJARAT)");
        arrBranch.add("MEHSANA (GUJARAT)");
        arrBranch.add("PALANPUR (GUJARAT)");
        arrBranch.add("PATAN (GUJARAT)");
        arrBranch.add("SURENDRANAGAR (GUJARAT)");
        arrBranch.add("DEPALPUR (MP)");
        arrBranch.add("DEWAS (MP)");
        arrBranch.add("DHAMNOD (MP)");
        arrBranch.add("INDORE (MP)");
        arrBranch.add("KHARGONE (MP)");
        arrBranch.add("SHUJALPUR (MP)");
        arrBranch.add("UJJAIN (MP)");
        arrBranch.add("AGAR MALWA (MP)");
        arrBranch.add("ALOT (MP)");
        arrBranch.add("ASHTA (MP)");
        arrBranch.add("MANDSAUR (MP)");
        arrBranch.add("NEEMUCH (MP)");
        arrBranch.add("RATLAM (MP)");
        arrBranch.add("SHAMGARH (MP)");
        ArrayAdapter<String> adater_spring_branch = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, arrBranch);
        branch_spinners.setAdapter((adater_spring_branch));

        btn_otp = findViewById(R.id.visitor_otp);
        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder deletedialog = new AlertDialog.Builder(EmployeeRegistratinActivity.this);
                deletedialog.setTitle("Alert?");
                deletedialog.setIcon(R.drawable.baseline_notifications_24);
                deletedialog.setMessage("Are you sure want to Proceeds");
                deletedialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(EmployeeRegistratinActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();}
                });
                deletedialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EmployeeRegistratinActivity.this, "Item Not Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                deletedialog.show();
                intent = new Intent(EmployeeRegistratinActivity.this,Otpverification.class);
                startActivity(intent);
            }
        });

     TextView   tv = (TextView) this.findViewById(R.id.mywidget);
        tv.setSelected(true);
    }
}