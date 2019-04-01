package ApiManager;

public interface ApiListener<AnyType> {
    void onSuccess(AnyType result);
    void onError(String result);

}
