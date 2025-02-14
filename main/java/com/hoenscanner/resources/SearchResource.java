package com.hoenscanner.resources;

import com.hoenscanner.model.Search;
import com.hoenscanner.model.SearchResult;
import com.hoenscanner.HoenScannerApplication;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/search")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

    @POST
    public Response search(Search search) {
        List<SearchResult> filteredResults = HoenScannerApplication.getSearchResults().stream()
                .filter(result -> result.getCity().equalsIgnoreCase(search.getCity()))
                .collect(Collectors.toList());

        return Response.ok(filteredResults).build();
    }
}
