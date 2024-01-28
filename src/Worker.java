public class Worker {
    private final OnTaskDoneListener callback;
    private final OnTaskErrorListener errorCallback;

    public Worker(OnTaskDoneListener callback, OnTaskErrorListener errorCallback) {
        this.callback = callback;
        this.errorCallback = errorCallback;
    }

    public void start() {

        try {
            for (int i = 0; i < 100; i++) {
                if (i == 32) {
                    throw new Exception(String.valueOf(i + 1));
                }
                callback.onDone("Task " + i + " is done");
            }
        } catch (Exception e) {
            errorCallback.onError("Task " + e.getMessage() + " done with errors");
            for (int i = Integer.parseInt(e.getMessage()) - 1; i < 100; i++) {
                callback.onDone("Task " + i + " is done");
            }
        }

    }
}
