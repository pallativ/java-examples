# Examples of CompletableFuture
##thenAccept : 
_thenApply_ used to accept the result from previous method in the chain.It accepts the value from previous stage and return nothing.
###Use cases of thenAccept
1. Accept the value, return nothing.
##exceptionally
This method is used to handle the exceptions occurred in the previous stages.
### Use cases of exceptionally
1. If you want to handle exception and continue to next stages in the chain.
2. In case of the exception, if you want to stop the execution of next stages, 
you should add exceptionally block at the end. 
