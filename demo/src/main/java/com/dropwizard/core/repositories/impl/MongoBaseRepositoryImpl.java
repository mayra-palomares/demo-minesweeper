package com.dropwizard.core.repositories.impl;

import com.dropwizard.core.mapper.BaseMapper;
import com.dropwizard.core.models.BaseModel;
import com.dropwizard.core.repositories.BaseRepository;
import com.dropwizard.db.MongoDBManaged;
import com.google.inject.Inject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MongoBaseRepositoryImpl<T extends BaseModel> implements BaseRepository<T> {
    private MongoCollection<Document> collection;
    private BaseMapper<T> mapper;

    @Inject
    public MongoBaseRepositoryImpl(String collectionName, MongoDBManaged mongoDBManaged, BaseMapper<T> mapper) {
        this.collection = mongoDBManaged.getCollectionByName(collectionName);
        this.mapper = mapper;
    }

    @Override
    public List<T> list() {
        final MongoCursor<Document> documents = collection.find().iterator();
        final List<T> entities = new ArrayList<>();

        try {
            while (documents.hasNext()) {
                final Document document = documents.next();
                entities.add(mapper.map(document));
            }
        } finally {
            documents.close();
        }
        return entities;
    }

    @Override
    public T getById(String entityId) {
        Document query = new Document("_id", new ObjectId(entityId));
        Optional<Document> document = Optional.ofNullable(collection.find(query).first());
        return document.isPresent() ? mapper.map(document.get()) : null;
    }

    @Override
    public T save(T entity) {
        Document newDocument = mapper.map(entity);
        collection.insertOne(newDocument);
        return getDocument(newDocument);
    }

    @Override
    public T update(String entityId, T entity) {
        Document updatedDocument = mapper.map(entity);
        Document query = new Document("_id", new ObjectId(entityId));
        collection.updateOne(query, new Document("$set", updatedDocument));
        return getDocument(updatedDocument);
    }

    private T getDocument(Document document) {
        Optional<Document> documentFind = Optional.ofNullable(collection.find(document).first());
        return documentFind.isPresent() ? mapper.map(documentFind.get()) : null;
    }

    @Override
    public void removeById(String entityId) {
        T entity = getById(entityId);
        if(entity != null) {
            entity.setDeleted(true);
            Document updatedDocument = mapper.map(entity);
            Document query = new Document("_id", new ObjectId(entityId));
            collection.updateOne(query, new Document("$set", updatedDocument));
        }
    }
}
