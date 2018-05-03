package com.wei.mvp.network.response;


import java.util.List;

public class BaseResponse<T>
{
    private boolean error;
    private List<T> results;

    public boolean isSuccess()
    {
        return !error;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
