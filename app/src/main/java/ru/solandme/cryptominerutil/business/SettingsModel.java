package ru.solandme.cryptominerutil.business;

import android.os.AsyncTask;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        SharedPrefService.setBooleanPreference(MyApp.getContext(), key, isActive);
    }

    private class LoadHashratesTask extends AsyncTask<Void, Void, HashMap<String, Algo>> {

        protected HashMap<String, Algo> doInBackground(Void[] params) {
            HashMap<String, Algo> algos = new HashMap<>();

            List<String> algoNames = Arrays.asList(
                    "Bitcore", "Blake2s", "Blakcoin", "C11", "Decred", "Equihash", "Groestl",
                    "Hmq1725", "Jha", "Keccak", "Lbry", "Lyra2v2", "Lyra2z", "M7m",
                    "Myr-gr", "Neoscrypt", "Nist5", "Quark", "Scrypt", "Sha256", "Sib", "Skein",
                    "Skunk", "Timetravel", "Tribus", "Veltor", "X11", "X11evo", "X13", "X14", "X15",
                    "X17", "Xevan", "Yescrypt");

            for (String name : algoNames) {
                algos.put(name.toLowerCase(), new Algo(name,
                        SharedPrefService.getLongPreference(MyApp.getContext(), name.toLowerCase() + "_hr", 0),
                        SharedPrefService.getBooleanPreference(MyApp.getContext(), name.toLowerCase() + "_is_active", true)));
            }

            return algos;
        }

        protected void onPostExecute(HashMap<String, Algo> algos) {
            callBack.onAlgosReceived(algos);
        }
    }
}
