package BusinessLogic.Interfaces;

import Shared.Helpers.ResponseHandler.ResponseHandler;

public interface IRequestHandler {
    ResponseHandler<?> handle(String json);
}
