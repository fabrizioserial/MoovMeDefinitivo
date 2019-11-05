package Assets;

import java.util.ArrayList;

public class AssetBatch {
    private final ArrayList<Assets.Asset> assetList;
    private final Integer code;
    private final Assets.AssetType type;


    public AssetBatch(Assets.AssetType assetType, int cuantity, Integer newCode, int precioDeAlquilerDelLote){
        assetList = new ArrayList<Assets.Asset>();
        for (int i = 0; i <cuantity; i++) {
            assetList.add(new Assets.Asset(assetType,precioDeAlquilerDelLote));
        }
        this.code=newCode;
        this.type=assetType;
    }

    public ArrayList<Assets.Asset> getAssetList() {
        return assetList;
    }

    public Integer getCode() {
        return code;
    }

    public Assets.AssetType getType() {
        return type;
    }
}
