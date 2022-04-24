package mongoose.base.client.aggregates.cart;

import mongoose.base.client.aggregates.event.EventAggregate;
import mongoose.base.shared.entities.Cart;
import mongoose.base.shared.entities.Document;
import mongoose.base.shared.entities.MoneyTransfer;
import dev.webfx.framework.shared.orm.domainmodel.DataSourceModel;
import dev.webfx.framework.shared.orm.entity.EntityList;
import dev.webfx.platform.shared.async.Future;

import java.util.List;

/**
 * @author Bruno Salmon
 */
public interface CartAggregate {

    static CartAggregate get(Object cartIdOrUuid) {
        return CartAggregateImpl.get(cartIdOrUuid);
    }

    static CartAggregate getOrCreate(Object cartIdOrUuid, DataSourceModel dataSourceModel) {
        return CartAggregateImpl.getOrCreate(cartIdOrUuid, dataSourceModel);
    }

    static CartAggregate getOrCreateFromCart(Cart cart) {
        return CartAggregateImpl.getOrCreateFromCart(cart);
    }

    static CartAggregate getOrCreateFromDocument(Document document) {
        return CartAggregateImpl.getOrCreateFromDocument(document);
    }

    Future<Cart> onCart();

    Cart getCart();

    Future<List<Document>> onCartDocuments();

    List<Document> getCartDocuments();

    Future<EntityList> onCartPayments();

    EntityList<MoneyTransfer> getCartPayments();

    void unload();

    boolean isLoading();

    boolean isLoaded();

    EventAggregate getEventAggregate();

}
