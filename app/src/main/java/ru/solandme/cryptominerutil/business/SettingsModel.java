package ru.solandme.cryptominerutil.business;

import android.os.AsyncTask;

import java.util.HashMap;

import ru.solandme.cryptominerutil.MyApp;
import ru.solandme.cryptominerutil.business.pojo.Algo;
import ru.solandme.cryptominerutil.data.SharedPrefService;

public class SettingsModel implements ISettingsModel {
    private ISettingsModel.CallBack callBack;

    public SettingsModel(ISettingsModel.CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void getAlgos() {
        new LoadHashratesTask().execute();
    }

    @Override
    public void saveAlgoActive(String key, boolean isActive) {
        SharedPrefService.setBooleanPreference(MyApp.getContext(),key, isActive);
    }

    private class LoadHashratesTask extends AsyncTask<Void, Void, HashMap<String, Algo>> {

        protected HashMap<String, Algo> doInBackground(Void[] params) {
            HashMap<String, Algo> algos = new HashMap<>();

            algos.put("bitcore", new Algo(
                    "Bitcore",
                    SharedPrefService.getLongPreference(MyApp.getContext(), "bitcore_hr", 0),
                    SharedPrefService.getBooleanPreference(MyApp.getContext(), "bitcore_is_active", true)));

            algos.put("blake2s", new Algo("Blake2s",
                    SharedPrefService.getLongPreference(MyApp.getContext(), "blake2s_hr", 0),
                    SharedPrefService.getBooleanPreference(MyApp.getContext(), "blake2s_is_active", true)));

            algos.put("blakcoin", new Algo("Blakcoin",
                    SharedPrefService.getLongPreference(MyApp.getContext(), "blakecoin_hr", 0),
                    SharedPrefService.getBooleanPreference(MyApp.getContext(), "blakecoin_is_active", true)));

            algos.put("c11", new Algo("C11",
                    SharedPrefService.getLongPreference(MyApp.getContext(), "c11_hr", 0),
                    SharedPrefService.getBooleanPreference(MyApp.getContext(), "c11_is_active", true)));



//            algos.put("decred", new Algo("Decred", SharedPrefService.getLongPreference(MyApp.getContext(), "decred_hr", 0)));
//            algos.put("equihash", new Algo("Equihash", SharedPrefService.getLongPreference(MyApp.getContext(), "equihash_hr", 0)));
//            algos.put("groestl", new Algo("Groestl", SharedPrefService.getLongPreference(MyApp.getContext(), "groestl_hr", 0)));
//            algos.put("hmq1725", new Algo("Hmq1725", SharedPrefService.getLongPreference(MyApp.getContext(), "hmq1725_hr", 0)));
//            algos.put("jha", new Algo("Jha", SharedPrefService.getLongPreference(MyApp.getContext(), "jha_hr", 0)));
//            algos.put("keccak", new Algo("Keccak", SharedPrefService.getLongPreference(MyApp.getContext(), "keccak_hr", 0)));
//            algos.put("lbry", new Algo("Lbry", SharedPrefService.getLongPreference(MyApp.getContext(), "lbry_hr", 0)));
//            algos.put("lyra2v2", new Algo("Lyra2v2", SharedPrefService.getLongPreference(MyApp.getContext(), "lyra2v2_hr", 0)));
//            algos.put("lyra2z", new Algo("Lyra2z", SharedPrefService.getLongPreference(MyApp.getContext(), "lyra2z_hr", 0)));
//            algos.put("m7m", new Algo("M7m", SharedPrefService.getLongPreference(MyApp.getContext(), "m7m_hr", 0)));
//            algos.put("myr-gr", new Algo("Myr-gr", SharedPrefService.getLongPreference(MyApp.getContext(), "myr-gr_hr", 0)));
//            algos.put("neoscrypt", new Algo("Neoscrypt", SharedPrefService.getLongPreference(MyApp.getContext(), "neoscrypt_hr", 0)));
//            algos.put("nist5", new Algo("Nist5", SharedPrefService.getLongPreference(MyApp.getContext(), "nist5_hr", 0)));
//            algos.put("quark", new Algo("Quark", SharedPrefService.getLongPreference(MyApp.getContext(), "quark_hr", 0)));
//            algos.put("scrypt", new Algo("Scrypt", SharedPrefService.getLongPreference(MyApp.getContext(), "scrypt_hr", 0)));
//            algos.put("sha256", new Algo("Sha256", SharedPrefService.getLongPreference(MyApp.getContext(), "sha256_hr", 0)));
//            algos.put("sib", new Algo("Sib", SharedPrefService.getLongPreference(MyApp.getContext(), "sib_hr", 0)));
//            algos.put("skein", new Algo("Skein", SharedPrefService.getLongPreference(MyApp.getContext(), "skein_hr", 0)));
//            algos.put("skunk", new Algo("Skunk", SharedPrefService.getLongPreference(MyApp.getContext(), "skunk_hr", 0)));
//            algos.put("timetravel", new Algo("Timetravel", SharedPrefService.getLongPreference(MyApp.getContext(), "timetravel_hr", 0)));
//            algos.put("tribus", new Algo("Tribus", SharedPrefService.getLongPreference(MyApp.getContext(), "tribus_hr", 0)));
//            algos.put("veltor", new Algo("Veltor", SharedPrefService.getLongPreference(MyApp.getContext(), "veltor_hr", 0)));
//            algos.put("x11", new Algo("X11", SharedPrefService.getLongPreference(MyApp.getContext(), "x11_hr", 0)));
//            algos.put("x11evo", new Algo("X11evo", SharedPrefService.getLongPreference(MyApp.getContext(), "x11evo_hr", 0)));
//            algos.put("x13", new Algo("X13", SharedPrefService.getLongPreference(MyApp.getContext(), "x13_hr", 0)));
//            algos.put("x14", new Algo("X14", SharedPrefService.getLongPreference(MyApp.getContext(), "x14_hr", 0)));
//            algos.put("x15", new Algo("X15", SharedPrefService.getLongPreference(MyApp.getContext(), "x15_hr", 0)));
//            algos.put("x17", new Algo("X17", SharedPrefService.getLongPreference(MyApp.getContext(), "x17_hr", 0)));
//            algos.put("xevan", new Algo("Xevan", SharedPrefService.getLongPreference(MyApp.getContext(), "xevan_hr", 0)));
//            algos.put("yescrypt", new Algo("Yescrypt", SharedPrefService.getLongPreference(MyApp.getContext(), "yescrypt_hr", 0)));

            return algos;
        }

        protected void onPostExecute(HashMap<String, Algo> algos) {
            callBack.onAlgosReceived(algos);
        }
    }
}
