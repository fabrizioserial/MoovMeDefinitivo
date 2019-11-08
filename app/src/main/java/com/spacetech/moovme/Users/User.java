package com.spacetech.moovme.Users;


import com.spacetech.moovme.Assets.AssetParking;
import com.spacetech.moovme.Assets.AssetType;
import com.spacetech.moovme.Assets.Fee;
import com.spacetech.moovme.Assets.Travel;
import com.spacetech.moovme.Exceptions.AssetTypeDoesNotExistInSpecifiedZoneException;
import com.spacetech.moovme.Exceptions.CantApplyDiscountException;
import com.spacetech.moovme.Exceptions.UserIsAlreadyLockedException;
import com.spacetech.moovme.Exceptions.UserCantStartNewTrip;
import com.spacetech.moovme.Exceptions.UserIsNotInATripException;
import com.spacetech.moovme.Points.Points;


public class User extends Operators {


    private final Data data;
    private boolean isLocked=false;
    double money;
    Points points;

    Travel actualTravel=null;

    public User(Data data) {  //user se crea con datos que contienen su nombre y su numero de telefono
        this.data =data;
        this.points=new Points(0);
        money=0;
    }

    public void userLocking(boolean lockUser) throws UserIsAlreadyLockedException { //este metodo bloquea el usuario
        if(this.isLocked=lockUser){
            throw new UserIsAlreadyLockedException(); //explota si bloqueas algo bloqueado
        }
        else{
            isLocked=lockUser;
        }
    }

    public PhoneNumber getPhoneNumber(){
        return data.getPhoneNumber();
    }

    public String getName() {
        return data.getName();
    }

    public boolean getLock(){ //for testing
        return isLocked;
    }

    public void rentAsset(AssetParking assetParking, AssetType assetType, int expectedTimeInMinutes) throws AssetTypeDoesNotExistInSpecifiedZoneException, UserCantStartNewTrip {
        if(actualTravel != null&&getLock()){ //pregunta si esta bloqueado o esta en un viaje
            throw new UserCantStartNewTrip();
        } else {
            try {
                actualTravel = new Travel(assetParking.rentAsset(assetType),expectedTimeInMinutes); //inicia un nuevo viaje pidiendo el asset al puesto de assets
            } catch (AssetTypeDoesNotExistInSpecifiedZoneException assetTypeDoesNotExistInSpecifiedZoneException) {
                actualTravel = null;
                throw new AssetTypeDoesNotExistInSpecifiedZoneException();
            }
        }
    }

    public void returnAsset(AssetParking assetParking)throws UserIsNotInATripException { //devuelve el asset al estacionamiento
        if(actualTravel!=null){
            Fee fee = assetParking.returnAsset(actualTravel,this); //aca le dicen cuanto tiene q pagar (entren en los metodos de asset parking para ver q pasa)
            if(assetParking.canApplyDiscount(actualTravel,this)){//pregunta si puede aplicar descuento
                try {
                    fee = assetParking.applyDiscount(actualTravel,this,fee); //le devuelve lo q tiene q pagar pero con descuento
                } catch (CantApplyDiscountException cantApplyDiscountException) {
                    throw new RuntimeException("Situacion imposible ya que se chequeo antes que esto sea posible");//explota por razones obias
                }
            }
            money= money-fee.getPrice(); //le gasta su plata
            actualTravel=null; //deja vacio el viaje para poder hacer otro
        }
        else{
            throw new UserIsNotInATripException();
        }
    }

    public void returnAssetTimeTest(AssetParking assetParking,int time)throws UserIsNotInATripException {
        if(actualTravel!=null){
            Fee fee = assetParking.returnAssetTimeTest(actualTravel,this,time); //points had already been added here
            if(assetParking.canApplyDiscount(actualTravel,this)){
                try {
                    fee = assetParking.applyDiscount(actualTravel,this,fee);
                } catch (CantApplyDiscountException cantApplyDiscountException) {
                    throw new RuntimeException("Situacion imposible ya que se chequeo antes que esto sea posible");
                }
            }
            money= money-fee.getPrice(); //TODO spend money from user
            actualTravel=null;
        }
        else{
            throw new UserIsNotInATripException();
        }
    }




    public boolean equals(Object o1){
        if(o1 instanceof User){
            return ((User) o1).getPhoneNumber().getNumber()==data.getPhoneNumber().getNumber();
        }
        else return false;
    }

    public Data getData() {
        return data;
    }

    public void addPoints(Points calculateAquiredPoints) {
        points.add(calculateAquiredPoints);
    }

    public Points getPoints() {
        return points;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public Travel getActualTravel() {
        return actualTravel;
    }
}

