function YesNoModal(props) {
    return (
      <Modal
        {...props}
        size="lg"
        aria-labelledby="contained-modal-title-vcenter"
        centered
      >
        <Modal.Header closeButton>
          <Modal.Title id="contained-modal-title-vcenter">
            Warning!
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <h4>Remove product from cart</h4>
          <p>
            Do you want to remove this product out of your list cart?
          </p>
        </Modal.Body>
        <Modal.Footer>
          <Button color="darger" onClick={props.onHide}>Yes</Button>
          <Button onClick={props.onHide}>No</Button>
        </Modal.Footer>
      </Modal>
    );
  }