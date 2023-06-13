package BusinessLogic.Interfaces;

import Shared.Helpers.ResponseHandler.ResponseHandler;

public interface IRequestHandler {
    String handle(String json) throws Exception;
}
