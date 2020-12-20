package com.app.meli.appmeli;

import static org.assertj.core.api.Assertions.assertThat;

import com.app.meli.appmeli.controllers.CouponController;
import com.app.meli.appmeli.models.CouponRequest;
import com.app.meli.appmeli.models.CouponResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CouponTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CouponController couponController;

    @Test
    public void shouldReturn200WhenCanBuy() throws Exception {
        this.mockMvc
                .perform(
                    post("/coupon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                            "{" +
                                "\"item_ids\" : [\"MLA661000574\",\"MLA882180163\",\"MLA784757659\"]," +
                                "\"amount\" : 10000.0" +
                            "}"
                        )
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn400WhenCantBuy() throws Exception {
        this.mockMvc
                .perform(
                        post("/coupon")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        "{" +
                                                "\"item_ids\" : [\"MLA661000574\",\"MLA882180163\",\"MLA784757659\"]," +
                                                "\"amount\" : 20.0" +
                                                "}"
                                )
                )
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void verifyItemsAndAmountReturned() throws Exception {
        List<String> items = new ArrayList<>();
        items.add("MLA661000574");
        items.add("MLA882180163");
        items.add("MLA784757659");

        Float monto = (float) 1300;

        CouponResponse couponResponse = couponController.calculateCoupon(new CouponRequest(items,monto));

        assertThat(couponResponse.getTotal()).isEqualTo((float) 1289);
        List<String> expectedItems = new ArrayList<>();
        expectedItems.add("MLA882180163");
        expectedItems.add("MLA784757659");

        assertThat(couponResponse.getItems()).containsExactlyInAnyOrder("MLA882180163","MLA784757659");
    }
}
